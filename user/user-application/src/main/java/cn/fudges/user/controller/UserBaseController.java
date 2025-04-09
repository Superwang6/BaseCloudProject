package cn.fudges.user.controller;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.response.UserBaseResponse;
import cn.fudges.user.service.UserBaseService;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@RestController
@RequestMapping("/user/user-base")
@RequiredArgsConstructor
public class UserBaseController {

    private final UserBaseService userBaseService;

    @PostMapping("/login")
    public ResultResponse<UserBaseResponse> queryUserByUsername(@RequestBody UserBaseRequest request){
        return ResultResponse.success(BeanUtil.copyProperties(userBaseService.queryUserByUsername(request), UserBaseResponse.class));
    }

    @PostMapping("/detail/{userId}")
    public ResultResponse<UserBaseResponse> detail(@PathVariable("userId") Long userId) {
        return ResultResponse.success(BeanUtil.copyProperties(userBaseService.queryUserByUserId(userId), UserBaseResponse.class));
    }
}

