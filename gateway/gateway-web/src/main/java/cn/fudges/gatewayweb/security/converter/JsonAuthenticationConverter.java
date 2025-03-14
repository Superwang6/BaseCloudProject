package cn.fudges.gatewayweb.security.converter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 王平远
 * @since 2025/3/13
 */
public class JsonAuthenticationConverter implements ServerAuthenticationConverter {

    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        if(!request.getMethod().matches("POST") || !MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))){
            return Mono.empty();
        }
        return request.getBody()
                .next()
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    JSONObject jsonObject = JSON.parseObject(bytes);
                    String username = jsonObject.getString(USERNAME_KEY);
                    String password = jsonObject.getString(PASSWORD_KEY);
                    return Mono.just(new UsernamePasswordAuthenticationToken(username, password));
                });
    }
}
