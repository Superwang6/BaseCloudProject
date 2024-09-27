package cn.fudges.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.fudges.oauth2.dao")
@EnableFeignClients(basePackages = "cn.fudges.*.api")
public class Oauth2AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthApplication.class, args);
	}

}
