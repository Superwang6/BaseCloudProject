package cn.fudges.role.controller.apiImpl;

import cn.fudges.role.api.RoleApi;
import cn.fudges.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@RestController
@RequiredArgsConstructor
public class RoleApiImpl implements RoleApi {

    private final RoleService roleService;

}

