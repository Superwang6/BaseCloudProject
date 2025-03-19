package cn.fudges.role.api;

import cn.fudges.common.result.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@FeignClient(name = "role", contextId = "roleApi")
public interface RoleApi {

}

