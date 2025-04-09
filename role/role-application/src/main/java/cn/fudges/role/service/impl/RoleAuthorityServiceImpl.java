package cn.fudges.role.service.impl;

import cn.fudges.common.result.ResultCodeEnum;
import cn.fudges.common.utils.AssertUtils;
import cn.fudges.role.dao.RoleAuthorityDao;
import cn.fudges.role.entity.RoleUser;
import cn.fudges.role.entity.po.AuthorityPo;
import cn.fudges.role.entity.po.RoleUserPo;
import cn.fudges.role.service.AuthorityService;
import cn.fudges.role.service.RoleAuthorityService;
import cn.fudges.role.entity.po.RoleAuthorityPo;
import cn.fudges.role.dao.po.RoleAuthorityPoMapper;
import cn.fudges.role.service.RoleService;
import cn.fudges.role.service.RoleUserService;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Service
@RequiredArgsConstructor
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    private final RoleService roleService;

    private final RoleUserService roleUserService;

    private final AuthorityService authorityService;

    private final RoleAuthorityDao roleAuthorityDao;

    @Override
    public List<Integer> queryAuthorityIdListByUserId(Long userId) {
        AssertUtils.isNotNull(userId, ResultCodeEnum.PARAM_ERROR);

        QueryWrapper<RoleUserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<RoleUserPo> roleUserPoList = roleUserService.list(wrapper);
        Set<Long> roleIdSet = roleUserPoList.stream().map(RoleUserPo::getRoleId).collect(Collectors.toSet());

        Boolean isAdmin = roleService.checkHasAdminRole(Lists.newArrayList(roleIdSet));
        if(isAdmin) {
            // 如果是超级管理员，则返回所有可见的叶子节点权限id
            QueryWrapper<AuthorityPo> authorityWrapper = new QueryWrapper<>();
            authorityWrapper.eq("is_leaf", 1).eq("is_visible", 1).orderByAsc("id");
            List<AuthorityPo> authorityPoList = authorityService.list(authorityWrapper);
            return authorityPoList.stream().map(AuthorityPo::getId).collect(Collectors.toList());
        } else {
            QueryWrapper<RoleAuthorityPo> roleAuthorityWrapper = new QueryWrapper<>();
            roleAuthorityWrapper.in("role_id", roleIdSet).orderByAsc("id");
            List<RoleAuthorityPo> roleAuthorityPoList = roleAuthorityDao.list(roleAuthorityWrapper);
            return roleAuthorityPoList.stream().map(RoleAuthorityPo::getAuthorityId).collect(Collectors.toList());
        }
    }
}

