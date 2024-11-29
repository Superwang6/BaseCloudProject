package cn.fudges.oauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王平远
 * @since 2024/11/29
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }
}
