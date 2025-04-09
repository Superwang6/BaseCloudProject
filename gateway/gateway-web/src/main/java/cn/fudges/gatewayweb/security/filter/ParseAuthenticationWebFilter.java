package cn.fudges.gatewayweb.security.filter;

import cn.fudges.common.utils.authentication.AuthenticationUtils;
import cn.fudges.gateway.common.enums.GatewayRedisKey;
import cn.fudges.gatewayweb.mode.UserDetail;
import cn.fudges.gatewayweb.security.token.UsernamePasswordAuthenticationToken;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Context context = createContext(exchange);
        if(ObjectUtil.isNotNull(context)) {
            return chain.filter(exchange).contextWrite(context);
        } else {
            return chain.filter(exchange);
        }
    }

    private Context createContext(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(StrUtil.isNotBlank(authorization)) {
            long userId = Long.parseLong(AuthenticationUtils.decode(authorization));
            RBucket<UserDetail> userBucket = redissonClient.getBucket(GatewayRedisKey.USER_USER_DETAIL_PREFIX + userId);
            if(userBucket.isExists()) {
                UserDetail detail = userBucket.get();
                if(ObjectUtil.isNotNull(detail)) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(detail, detail.getPassword(), detail.getAuthorities(), detail.getPlatform());
                    SecurityContextImpl securityContext = new SecurityContextImpl();
                    securityContext.setAuthentication(token);
                    return ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext));
                }
            }
        }
        return null;
    }
}
