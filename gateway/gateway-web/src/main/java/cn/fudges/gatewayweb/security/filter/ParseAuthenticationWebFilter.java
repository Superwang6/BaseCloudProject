package cn.fudges.gatewayweb.security.filter;

import cn.fudges.common.enums.RequestEnum;
import cn.fudges.gateway.common.enums.GatewayRedisKey;
import cn.fudges.gatewayweb.mode.UserDetail;
import cn.fudges.gatewayweb.security.token.UsernamePasswordAuthenticationToken;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * @author 王平远
 * @since 2025/4/8
 */
@Component
@RequiredArgsConstructor
public class ParseAuthenticationWebFilter implements WebFilter {

    private final RedissonClient redissonClient;

    private static final String AUTHORIZATION = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return createContext(exchange)
                .defaultIfEmpty(Context.empty())
                .flatMap(ctx -> chain.filter(exchange).contextWrite(ctx));
    }

    private Mono<Context> createContext(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
        if (StrUtil.isBlank(authorization)) {
            return Mono.empty();
        }

        String tokenKey = GatewayRedisKey.USER_LOGIN_TOKEN_USER_PREFIX + authorization;
        RBucket<Long> userIdBucket = redissonClient.getBucket(tokenKey);

        return Mono.fromCallable(userIdBucket::get)
                .filter(ObjectUtil::isNotNull)
                .flatMap(userId -> {
                    String userKey = GatewayRedisKey.USER_LOGIN_USER_DETAIL_PREFIX + userId;
                    RBucket<UserDetail> userDetailBucket = redissonClient.getBucket(userKey);
                    return Mono.fromCallable(userDetailBucket::get);
                })
                .filter(ObjectUtil::isNotNull)
                .map(detail -> {
                    exchange.getRequest().mutate().header(RequestEnum.USER_ID, detail.getId().toString());

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            detail, detail.getPassword(), detail.getAuthorities(), detail.getPlatform());
                    SecurityContextImpl context = new SecurityContextImpl(token);
                    return ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context));
                });
    }
}
