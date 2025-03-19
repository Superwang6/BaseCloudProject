package cn.fudges.gatewayweb.security.handler;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.gateway.common.enums.GatewayRedisKey;
import cn.fudges.gatewayweb.mode.UserDetail;
import cn.fudges.user.response.UserBaseResponse;
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

        // 存入redis
        RBucket<Object> bucket = redissonClient.getBucket(GatewayRedisKey.USER_DETAIL_PREFIX + userDetail.getId());
        bucket.set(userDetail, Duration.ofDays(10));

        ResultResponse<?> res = ResultResponse.success(UserBaseResponse.builder().id(userDetail.getId()).nickName(userDetail.getNickName()).mobilePhone(userDetail.getMobilePhone()).tenantId(userDetail.getTenantId()).platform(userDetail.getPlatform()).build());
        ServerHttpResponse response = exchange.getExchange().getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);
        return response.writeWith(Mono.fromSupplier(() -> response.bufferFactory().wrap(JSON.toJSONBytes(res))));
    }
}
