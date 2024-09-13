package cn.fudges.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/user/user-base")
public class UserBaseController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/test")
    public String test() {
        return "1111   :" + port;
    }

}

