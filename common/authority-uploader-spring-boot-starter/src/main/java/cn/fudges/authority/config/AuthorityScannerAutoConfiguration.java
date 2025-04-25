package cn.fudges.authority.config;

import cn.fudges.authority.annotation.Permission;
import cn.fudges.authority.client.impl.RedisCacheClient;
import cn.fudges.authority.client.inner.AuthorityCacheClient;
import cn.fudges.authority.scanner.PermissionScanner;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@Configuration
@ConditionalOnClass({Permission.class})
@ComponentScan("cn.fudges.authority")
@ConfigurationProperties(prefix = "authority.config")
public class AuthorityScannerAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(AuthorityCacheClient.class)
    @ConditionalOnBean(RedissonClient.class)
    public RedisCacheClient defaultRedisCacheClient(RedissonClient redissonClient) {
        return new RedisCacheClient(redissonClient);
    }

    @Bean
    public PermissionScanner permissionScanner(RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping, ApplicationIdConfiguration applicationIdConfiguration, AuthorityCacheClient cacheClient) {
        return new PermissionScanner(requestMappingInfoHandlerMapping, applicationIdConfiguration, cacheClient);
    }
}
