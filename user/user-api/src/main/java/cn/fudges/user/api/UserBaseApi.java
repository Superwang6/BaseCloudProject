package cn.fudges.user.api;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.response.UserBaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@FeignClient(name = "user-service")
public interface UserBaseApi {

    @PostMapping("/user/userBase/checkLoginUserNameAndPassword")
    ResultResponse<Boolean> checkLoginUserNameAndPassword(@RequestBody UserBaseRequest request);

    @GetMapping("/user/userBase/queryUserByUsername/{username}")
    ResultResponse<UserBaseResponse> queryUserByUsername(@PathVariable ("username") String username);
}

