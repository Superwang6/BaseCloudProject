package cn.fudges.user.controller;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.request.UserLoginRequest;
import cn.fudges.user.response.UserLoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王平远
 * @since 2024/9/29
 */
@RestController("/login")
public class LoginController {

    @PostMapping("/passwordLogin")
    public ResultResponse<UserLoginResponse> passwordLogin(UserLoginRequest request) {

        return null;
    }
}
