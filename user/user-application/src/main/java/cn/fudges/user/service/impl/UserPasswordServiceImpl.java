package cn.fudges.user.service.impl;

import cn.fudges.user.dao.UserPasswordDao;
import cn.fudges.user.entity.po.UserPasswordPo;
import cn.fudges.user.service.UserPasswordService;
import cn.fudges.user.entity.UserPassword;
import cn.fudges.user.dao.po.UserPasswordPoMapper;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户密码信息 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-10-09
 */
@Service
@RequiredArgsConstructor
public class UserPasswordServiceImpl implements UserPasswordService {

    private final UserPasswordDao userPasswordDao;


    @Override
    public UserPassword queryPasswordByUserId(Long userId) {
        QueryWrapper<UserPasswordPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        UserPasswordPo one = userPasswordDao.getOne(queryWrapper);
        return BeanUtil.copyProperties(one, UserPassword.class);
    }
}

