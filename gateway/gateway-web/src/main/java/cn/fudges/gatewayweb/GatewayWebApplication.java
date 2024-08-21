package cn.fudges.gatewayweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// openfeign的接口位于依赖服务的 xxx-api 模块中，需要配置扫描才可以初始化到
@EnableFeignClients(basePackages = "cn.fudges.*.api")
// 增加扫描引入的api模块的配置类，目前主要是openfeign的配置类
@ComponentScan(basePackages = {"cn.fudges.gatewayweb", "cn.fudges.baseapi.config"})
public class GatewayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

}
