package cn.fudges.user.controller.apiImpl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.api.UserBaseApi;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.response.UserBaseResponse;
import cn.fudges.user.service.UserBaseService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@RestController
public class UserBaseApiImpl implements UserBaseApi {

    @Autowired
    UserBaseService userBaseService;

    @Override
    public ResultResponse<Boolean> checkLoginUserNameAndPassword(UserBaseRequest request) {
        Boolean isCanLogin = userBaseService.checkLoginUserNameAndPassword(request);
        return ResultResponse.success(isCanLogin);
    }

    @Override
    public ResultResponse<UserBaseResponse> queryUserByUsername(String username) {
        return ResultResponse.success(BeanUtil.copyProperties(userBaseService.queryUserByUsername(username), UserBaseResponse.class));
    }
}

