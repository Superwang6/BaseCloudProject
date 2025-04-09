package cn.fudges.gatewayweb.config;

import cn.fudges.gatewayweb.security.entrypoint.JsonAuthenticationEntryPoint;
import cn.fudges.gatewayweb.security.filter.LoginAuthenticationWebFilter;
import cn.fudges.gatewayweb.security.filter.ParseAuthenticationWebFilter;
import cn.fudges.gatewayweb.security.handler.JsonAccessDeniedHandler;
import cn.fudges.gatewayweb.security.handler.JsonAuthenticationSuccessHandler;
import cn.fudges.gatewayweb.security.manager.UserDetailsReactiveAuthenticationManager;
import cn.fudges.gatewayweb.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public SecurityWebFilterChain authorizationServerSecurityFilterChain(ServerHttpSecurity http, LoginAuthenticationWebFilter loginAuthenticationWebFilter, ParseAuthenticationWebFilter parseAuthenticationWebFilter) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new JsonAccessDeniedHandler())
                        .authenticationEntryPoint(new JsonAuthenticationEntryPoint())
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .addFilterAt(loginAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterBefore(parseAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        ;
        return http.build();
    }

    @Bean(name = "loginAuthenticationWebFilter")
    public LoginAuthenticationWebFilter loginAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager, JsonAuthenticationSuccessHandler jsonAuthenticationSuccessHandler) {
        return new LoginAuthenticationWebFilter(authenticationManager, jsonAuthenticationSuccessHandler);
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(UserService userService, PasswordEncoder passwordEncoder) {
        return new UserDetailsReactiveAuthenticationManager(userService, passwordEncoder);
    }


    /**
     * 配置密码解析器，使用BCrypt的方式对密码进行加密和验证
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
