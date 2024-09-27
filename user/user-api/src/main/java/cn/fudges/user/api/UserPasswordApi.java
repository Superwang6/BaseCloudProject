package cn.fudges.user.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@FeignClient(name = "user")
public interface UserPasswordApi {

}

