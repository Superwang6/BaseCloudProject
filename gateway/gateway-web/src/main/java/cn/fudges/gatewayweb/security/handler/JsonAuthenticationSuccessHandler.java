package cn.fudges.gatewayweb.security.handler;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.gateway.common.enums.GatewayRedisKey;
import cn.fudges.gatewayweb.mode.UserDetail;
import cn.fudges.user.response.UserBaseResponse;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author 王平远
 * @since 2025/3/13
 */
@Component
@RequiredArgsConstructor
public class JsonAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private final RedissonClient redissonClient;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange exchange, Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        String token;
        // 存入userid -> 用户信息映射
        RBucket<Object> bucket = redissonClient.getBucket(GatewayRedisKey.USER_LOGIN_USER_DETAIL_PREFIX + userDetail.getId());
        RBucket<String> userBucket = redissonClient.getBucket(GatewayRedisKey.USER_LOGIN_USER_TOKEN_PREFIX + userDetail.getId());
        if (bucket.isExists()) {
            // 如果之前登陆过则延长登录时间
            bucket.expire(Duration.ofDays(10));
            userBucket.expire(Duration.ofDays(10));

            token = userBucket.get();
            RBucket<Long> tokenBucket = redissonClient.getBucket(GatewayRedisKey.USER_LOGIN_TOKEN_USER_PREFIX + token);
            tokenBucket.expire(Duration.ofDays(10));
        } else {
            bucket.set(userDetail, Duration.ofDays(10));

            token = IdUtil.fastSimpleUUID();
            userBucket.set(token, Duration.ofDays(10));

            RBucket<Long> tokenBucket = redissonClient.getBucket(GatewayRedisKey.USER_LOGIN_TOKEN_USER_PREFIX + token);
            tokenBucket.set(userDetail.getId(), Duration.ofDays(10));
        }

        ResultResponse<?> res = ResultResponse.success(
                UserBaseResponse.builder()
                        .id(userDetail.getId())
                        .nickName(userDetail.getNickName())
                        .mobilePhone(userDetail.getMobilePhone())
                        .tenantId(userDetail.getTenantId())
                        .platform(userDetail.getPlatform())
                        .authorityIdList(userDetail.getAuthorityIdList())
                        .authorization(token)
                        .build()
        );

        ServerHttpResponse response = exchange.getExchange().getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);
        return response.writeWith(Mono.fromSupplier(() -> response.bufferFactory().wrap(JSON.toJSONBytes(res))));
    }
}
