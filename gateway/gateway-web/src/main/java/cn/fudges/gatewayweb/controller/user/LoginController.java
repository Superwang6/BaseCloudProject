package cn.fudges.gatewayweb.controller.user;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.common.utils.AssertUtils;
import cn.fudges.user.api.UserBaseApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王平远
 * @since 2024/8/20
 */
@RestController
public class LoginController {

    @Resource
    private UserBaseApi userBaseApi;

    @GetMapping("/login/{id}")
    public ResultResponse login(@PathVariable("id") String id) {
        ResultResponse<String> userById = userBaseApi.getUserById(id);
        AssertUtils.isSuccess(userById);
        String data = userById.getData();
        return ResultResponse.success(data);
    }
}
