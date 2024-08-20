package cn.fudges.user.api;


import cn.fudges.common.result.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author wangpingyuan
 * @since 2024-08-20
 */
@FeignClient(name = "user")
public interface UserBaseApi {

    @GetMapping("/user/id")
    ResultResponse<String> getUserById();
}

