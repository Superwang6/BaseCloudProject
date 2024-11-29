package cn.fudges.user.service.impl;

import cn.fudges.user.entity.UserPassword;
import cn.fudges.user.entity.po.UserBasePo;
import cn.fudges.user.entity.po.UserPasswordPo;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.service.UserBaseService;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.dao.po.UserBasePoMapper;
import cn.fudges.user.service.UserPasswordService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserBaseServiceImpl extends ServiceImpl<UserBasePoMapper, UserBasePo> implements UserBaseService {

    @Autowired
    UserPasswordService userPasswordService;

    @Override
    public Boolean checkLoginUserNameAndPassword(UserBaseRequest request) {
        QueryWrapper<UserBasePo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", request.getUserName());
        wrapper.in("status", 0,2);
        wrapper.orderByAsc("id","create_time");
        List<UserBasePo> list = this.list(wrapper);
        if(CollUtil.isNotEmpty(list)) {
            UserBasePo userBase = list.get(0);
            QueryWrapper<UserPasswordPo> passwordQueryWrapper = new QueryWrapper<>();
            passwordQueryWrapper.eq("user_id", userBase.getId());
            UserPassword password = CglibUtil.copy(userPasswordService.getOne(passwordQueryWrapper), UserPassword.class);

            String md5Password = SecureUtil.md5(request.getPassword());
            return password.getLoginPassword().equals(md5Password);
        }
        return false;
    }

    @Override
    public UserBase queryUserByUsername(String username) {
        QueryWrapper<UserBasePo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        UserBasePo one = this.getOne(wrapper);
        UserBase base = BeanUtil.copyProperties(one, UserBase.class);

        if(ObjectUtil.isNotNull(base)) {
            QueryWrapper<UserPasswordPo> passwordQueryWrapper = new QueryWrapper<>();
            passwordQueryWrapper.eq("user_id", base.getId());
            UserPassword password = BeanUtil.copyProperties(userPasswordService.getOne(passwordQueryWrapper), UserPassword.class);
            base.setUserPassword(password);
        }
        return base;
    }
}

