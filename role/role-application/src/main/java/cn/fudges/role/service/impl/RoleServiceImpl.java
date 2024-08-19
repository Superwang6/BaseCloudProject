package cn.fudges.role.service.impl;

import cn.fudges.role.service.RoleService;
import cn.fudges.role.entity.Role;
import cn.fudges.role.dao.po.RolePoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-08-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RolePoMapper, Role> implements RoleService {

}

