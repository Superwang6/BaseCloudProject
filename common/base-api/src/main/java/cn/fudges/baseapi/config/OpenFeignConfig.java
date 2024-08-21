package cn.fudges.baseapi.config;

import feign.Logger;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

/**
 * @author 王平远
 * @since 2024/8/21
 */
@Configuration
@PropertySource("classpath:open-feign.properties")
public class OpenFeignConfig {
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectionPool(pool())
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .build();
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(200,5, TimeUnit.MINUTES);
    }
}
