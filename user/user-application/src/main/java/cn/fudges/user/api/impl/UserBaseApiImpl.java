package cn.fudges.user.api.impl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.api.UserBaseApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wangpingyuan
 * @since 2024-08-20
 */
@RestController
public class UserBaseApiImpl implements UserBaseApi {

    @GetMapping("/user/id")
    @Override
    public ResultResponse<String> getUserById() {
        return ResultResponse.success("12312312");
    }
}

