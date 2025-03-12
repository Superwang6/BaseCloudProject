package cn.fudges.gatewayweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author 王平远
 * @since 2024/9/29
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    @Order(1)
    public SecurityWebFilterChain authorizationServerSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/login").permitAll()
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }

}
