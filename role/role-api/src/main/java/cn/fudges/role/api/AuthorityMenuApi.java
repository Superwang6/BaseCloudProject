package cn.fudges.role.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@FeignClient(name = "role", contextId = "authorityMenuApi")
public interface AuthorityMenuApi {

}

