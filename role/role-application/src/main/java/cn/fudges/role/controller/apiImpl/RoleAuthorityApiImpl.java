package cn.fudges.role.controller.apiImpl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.role.api.RoleAuthorityApi;
import cn.fudges.role.service.RoleAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@RestController
@RequiredArgsConstructor
public class RoleAuthorityApiImpl implements RoleAuthorityApi {

    private final RoleAuthorityService roleAuthorityService;

    @Override
    public ResultResponse<List<Integer>> queryAuthorityIdListByUserId(Long userId) {
        return ResultResponse.success(roleAuthorityService.queryAuthorityIdListByUserId(userId));
    }
}

