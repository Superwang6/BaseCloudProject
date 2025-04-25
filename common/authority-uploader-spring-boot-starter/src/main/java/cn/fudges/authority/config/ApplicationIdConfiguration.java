package cn.fudges.authority.config;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王平远
 * @since 2025/4/20
 */
@Configuration
public class ApplicationIdConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer port;

    @Getter
    private String permissionApplicationId;

    @PostConstruct
    public void init() {
        permissionApplicationId = applicationName + ":" + port + ":" + IdUtil.fastSimpleUUID();
    }
}
