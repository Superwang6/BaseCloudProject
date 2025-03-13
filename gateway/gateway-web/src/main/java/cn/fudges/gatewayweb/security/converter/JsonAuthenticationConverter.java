package cn.fudges.gatewayweb.security.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

/**
 * @author 王平远
 * @since 2025/3/13
 */
public class JsonAuthenticationConverter implements ServerAuthenticationConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        if(!request.getMethod().matches("POST") || !MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))){
            return Mono.empty();
        }
        return request.getBody()
                .next()
                .flatMap(dataBuffer -> {
                    try {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        Map<String, String> authRequest = objectMapper.readValue(bytes, new TypeReference<>() {
                        });
                        String username = authRequest.get("username");
                        String password = authRequest.get("password");
                        return Mono.just(new UsernamePasswordAuthenticationToken(username, password));
                    } catch (IOException e) {
                        return Mono.empty();
                    }
                });
    }
}
