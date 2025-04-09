package cn.fudges.gatewayweb.config;

import cn.fudges.gatewayweb.handler.GlobalControllerExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

/**
 * @author 王平远
 * @since 2025/4/8
 */
@Configuration
@EnableConfigurationProperties({ServerProperties.class, WebProperties.class})
@RequiredArgsConstructor
public class ExceptionHandlerConfig {

    private final ServerProperties serverProperties;

    @Bean
    @Order(-1)
    public GlobalControllerExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                                     WebProperties webProperties, ObjectProvider<ViewResolver> viewResolvers,
                                                                     ServerCodecConfigurer serverCodecConfigurer, ApplicationContext applicationContext) {
        GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler(errorAttributes,
                webProperties.getResources(), this.serverProperties.getError(), applicationContext);
        exceptionHandler.setViewResolvers(viewResolvers.orderedStream().toList());
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

}