package cn.fudges.role.service;

import cn.fudges.role.entity.po.RoleAuthorityPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
public interface RoleAuthorityService {

    List<Integer> queryAuthorityIdListByUserId(Long userId);
}
