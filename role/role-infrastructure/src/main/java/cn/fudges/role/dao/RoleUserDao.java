package cn.fudges.role.dao;

import cn.fudges.role.entity.po.RoleUserPo;
import cn.fudges.role.dao.po.RoleUserPoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Service
public class RoleUserDao extends ServiceImpl<RoleUserPoMapper, RoleUserPo> {

}
