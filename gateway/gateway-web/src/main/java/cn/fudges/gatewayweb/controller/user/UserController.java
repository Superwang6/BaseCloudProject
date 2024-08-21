package cn.fudges.gatewayweb.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王平远
 * @since 2024/8/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/queryUser")
    public String queryUser() {
        return "queryUser";
    }
}
