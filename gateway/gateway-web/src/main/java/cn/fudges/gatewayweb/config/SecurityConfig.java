package cn.fudges.gatewayweb.config;

import cn.fudges.gatewayweb.security.converter.JsonAuthenticationConverter;
import cn.fudges.gatewayweb.security.handler.JsonAccessDeniedHandler;
import cn.fudges.gatewayweb.security.handler.JsonAuthenticationFailureHandler;
import cn.fudges.gatewayweb.security.handler.JsonAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

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
    public SecurityWebFilterChain authorizationServerSecurityFilterChain(ServerHttpSecurity http, UserDetailsRepositoryReactiveAuthenticationManager authenticationManager) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/login/passwordLogin").permitAll()
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new JsonAccessDeniedHandler())
//                        .authenticationEntryPoint()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .addFilterAt(jsonAuthenticationWebFilter(authenticationManager), SecurityWebFiltersOrder.FORM_LOGIN)
                .httpBasic(withDefaults())
//                .formLogin(withDefaults())
        ;
        return http.build();
    }

    public AuthenticationWebFilter jsonAuthenticationWebFilter(UserDetailsRepositoryReactiveAuthenticationManager authenticationManager) {
        AuthenticationWebFilter authFilter = new AuthenticationWebFilter(authenticationManager);
        authFilter.setServerAuthenticationConverter(new JsonAuthenticationConverter());
        authFilter.setAuthenticationSuccessHandler(new JsonAuthenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(new JsonAuthenticationFailureHandler());
        authFilter.setRequiresAuthenticationMatcher(new PathPatternParserServerWebExchangeMatcher("/login/passwordLogin"));
        return authFilter;
    }

    @Bean
    public UserDetailsRepositoryReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService reactiveUserDetailsService,PasswordEncoder passwordEncoder) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService);
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
