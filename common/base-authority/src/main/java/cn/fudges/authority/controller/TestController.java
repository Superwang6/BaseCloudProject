package cn.fudges.authority.controller;

import cn.fudges.authority.annotation.Login;
import cn.fudges.authority.annotation.PreAuthority;
import cn.fudges.authority.enums.Action;
import org.springframework.stereotype.Controller;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@Controller
@PreAuthority("111")
@Login(action = Action.SKIP)
public class TestController {

}
