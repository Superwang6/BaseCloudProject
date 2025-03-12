package cn.fudges.user.controller;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.response.UserBaseResponse;
import cn.fudges.user.service.UserBaseService;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/{username}")
    ResultResponse<UserBaseResponse> queryUserByUsername(@PathVariable("username") String username){
        return ResultResponse.success(BeanUtil.copyProperties(userBaseService.queryUserByUsername(username), UserBaseResponse.class));
    }
}

