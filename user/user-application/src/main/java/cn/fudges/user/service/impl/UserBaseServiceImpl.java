package cn.fudges.user.service.impl;

import cn.fudges.user.entity.UserPassword;
import cn.fudges.user.entity.po.UserBasePo;
import cn.fudges.user.entity.po.UserPasswordPo;
import cn.fudges.user.integration.role.RoleAuthorityIntegration;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.service.UserBaseService;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.dao.po.UserBasePoMapper;
import cn.fudges.user.service.UserPasswordService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户基础信息 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Service
@RequiredArgsConstructor
public class UserBaseServiceImpl extends ServiceImpl<UserBasePoMapper, UserBasePo> implements UserBaseService {

    private final UserPasswordService userPasswordService;

    private final RoleAuthorityIntegration roleAuthorityIntegration;

    @Override
    public UserBase queryUserByUsername(UserBaseRequest request) {
        QueryWrapper<UserBasePo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", request.getUserName()).eq("platform", request.getPlatform());
        UserBasePo one = getOne(wrapper);
        UserBase base = BeanUtil.copyProperties(one, UserBase.class);

        if(ObjectUtil.isNotNull(base)) {
            QueryWrapper<UserPasswordPo> passwordQueryWrapper = new QueryWrapper<>();
            passwordQueryWrapper.eq("user_id", base.getId());
            UserPassword password = BeanUtil.copyProperties(userPasswordService.getOne(passwordQueryWrapper), UserPassword.class);
            base.setUserPassword(password);

            List<Integer> authorityIdList = roleAuthorityIntegration.queryAuthorityIdListByUserId(base.getId());
            base.setAuthorityIdList(authorityIdList);
        }
        return base;
    }
}

