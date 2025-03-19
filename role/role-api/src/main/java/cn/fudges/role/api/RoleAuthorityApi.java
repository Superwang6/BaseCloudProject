package cn.fudges.role.api;

import cn.fudges.common.result.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@FeignClient(name = "role-service",contextId = "roleAuthorityApi")
public interface RoleAuthorityApi {

    @GetMapping("/role/authority/queryAuthorityIdListByUserId/{userId}")
    ResultResponse<List<Integer>> queryAuthorityIdListByUserId(@PathVariable("userId") Long userId);
}

