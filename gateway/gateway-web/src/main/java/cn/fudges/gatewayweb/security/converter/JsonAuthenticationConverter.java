package cn.fudges.gatewayweb.security.converter;

import cn.fudges.gatewayweb.security.token.UsernamePasswordAuthenticationToken;
import cn.fudges.gatewayweb.utils.GatewayHeaderUtils;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author 王平远
 * @since 2025/3/13
 */
public class JsonAuthenticationConverter implements ServerAuthenticationConverter {

    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    public static final String PLATFORM_KEY = "platform";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        if(!request.getMethod().matches("POST") || !MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(headers.getFirst(HttpHeaders.CONTENT_TYPE))){
            return Mono.empty();
        }
        Integer platform = ObjectUtil.isNotNull(GatewayHeaderUtils.getMetaData(exchange, PLATFORM_KEY))
                ? Integer.parseInt(Objects.requireNonNull(GatewayHeaderUtils.getMetaData(exchange, PLATFORM_KEY))) : 0;

        return request.getBody()
                .next()
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    JSONObject jsonObject = JSON.parseObject(bytes);
                    String username = jsonObject.getString(USERNAME_KEY);
                    String password = jsonObject.getString(PASSWORD_KEY);
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, platform);
                    return Mono.just(token);
                });
    }
}
