package cn.fudges.user.controller.apiImpl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.api.UserBaseApi;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.response.UserBaseResponse;
import cn.fudges.user.service.UserBaseService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.cglib.CglibUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@RestController
public class UserBaseApiImpl implements UserBaseApi {

    @Autowired
    UserBaseService userBaseService;

    @PostMapping("/user/userBase/checkLoginUserNameAndPassword")
    @Override
    public ResultResponse<Boolean> checkLoginUserNameAndPassword(@RequestBody UserBaseRequest request) {
        Boolean isCanLogin = userBaseService.checkLoginUserNameAndPassword(request);
        return ResultResponse.success(isCanLogin);
    }

    @Override
    public ResultResponse<UserBaseResponse> queryUserByUsername(String username) {
        return ResultResponse.success(CglibUtil.copy(userBaseService.queryUserByUsername(username), UserBaseResponse.class));
    }
}

