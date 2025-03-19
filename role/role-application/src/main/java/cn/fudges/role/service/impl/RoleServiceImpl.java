package cn.fudges.role.service.impl;

import cn.fudges.role.dao.RoleDao;
import cn.fudges.role.service.RoleService;
import cn.fudges.role.entity.po.RolePo;
import cn.fudges.role.dao.po.RolePoMapper;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RolePoMapper, RolePo> implements RoleService {

    private final RoleDao roleDao;

    @Override
    public Boolean checkHasAdminRole(List<Long> roleIdList) {
        QueryWrapper<RolePo> wrapper = new QueryWrapper<>();
        wrapper.in("id", roleIdList).eq("is_remove", 0).eq("is_admin", 1);
        List<RolePo> list = list(wrapper);
        return CollUtil.isNotEmpty(list);
    }
}

