package cn.fudges.authority.config;

import cn.fudges.authority.filter.AuthorizationFilter;
import cn.fudges.authority.property.SecurityProperties;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author 王平远
 * @since 2025/4/25
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    public SecurityFilterChain filterChain(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                        if(CollUtil.isNotEmpty(securityProperties.getIgnoreUrls())) {
                            auth.requestMatchers(securityProperties.getIgnoreUrls().toArray(new String[0])).permitAll();
                        }
                        auth.anyRequest().authenticated();
                    }
                )
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
