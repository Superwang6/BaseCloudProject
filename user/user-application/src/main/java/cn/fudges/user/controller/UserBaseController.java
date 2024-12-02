package cn.fudges.user.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@RestController
@RequestMapping("/user/user-base")
public class UserBaseController {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}

