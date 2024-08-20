package cn.fuges.gatewayweb.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王平远
 * @since 2024/8/20
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BaseCloudProject-Api文档")
                        .description("随便写点描述")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("wpy")
                                .email("1075227991@qq.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("我的博客")
                        .url("http://www.fudges.cn"));
    }
}
