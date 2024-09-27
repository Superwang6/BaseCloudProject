package cn.fudges.user.service.impl;

import cn.fudges.user.entity.UserPassword;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.service.UserBaseService;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.dao.po.UserBasePoMapper;
import cn.fudges.user.service.UserPasswordService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.SecureUtil;
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
public class UserBaseServiceImpl extends ServiceImpl<UserBasePoMapper, UserBase> implements UserBaseService {

    @Autowired
    UserPasswordService userPasswordService;

    @Override
    public Boolean checkLoginUserNameAndPassword(UserBaseRequest request) {
        QueryWrapper<UserBase> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", request.getUserName());
        wrapper.in("status", 0,2);
        wrapper.orderByAsc("id","create_time");
        List<UserBase> list = this.list(wrapper);
        if(CollUtil.isNotEmpty(list)) {
            UserBase userBase = list.get(0);
            QueryWrapper<UserPassword> passwordQueryWrapper = new QueryWrapper<>();
            passwordQueryWrapper.eq("user_id", userBase.getId());
            UserPassword password = userPasswordService.getOne(passwordQueryWrapper);

            String md5Password = SecureUtil.md5(password.getLoginSalt() + request.getPassword());
            return password.getLoginPassword().equals(md5Password);
        }
        return false;
    }
}

