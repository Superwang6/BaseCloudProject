package cn.fudges.role;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.fudges.role.dao")
public class RoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }

}
