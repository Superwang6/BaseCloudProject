package cn.fudges.role.service;

import cn.fudges.role.entity.po.RolePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
public interface RoleService extends IService<RolePo> {

    Boolean checkHasAdminRole(List<Long> roleIdList);
}
