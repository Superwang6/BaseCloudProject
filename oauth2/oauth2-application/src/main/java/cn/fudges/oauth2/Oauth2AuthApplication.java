package cn.fudges.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.fudges.oauth2.dao")
public class Oauth2AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthApplication.class, args);
	}

}
