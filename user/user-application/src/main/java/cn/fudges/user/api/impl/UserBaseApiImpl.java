package cn.fudges.user.api.impl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.api.UserBaseApi;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wangpingyuan
 * @since 2024-08-20
 */
@RestController
public class UserBaseApiImpl implements UserBaseApi {

    @Override
    public ResultResponse<String> getUserById(String id) {
//        AssertUtils.isTrue(false, ResultCodeEnum.PARAM_ERROR);
        return ResultResponse.success("success: " + id);
    }
}

