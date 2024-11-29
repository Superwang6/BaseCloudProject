package cn.fudges.oauth2.password;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 王平远
 * @since 2024/11/29
 */
@SpringBootTest
public class BCryptPasswordTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "123456";

        // 加密密码
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
