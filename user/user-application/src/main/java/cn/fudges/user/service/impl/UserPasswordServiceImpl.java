package cn.fudges.user.service.impl;

import cn.fudges.user.entity.po.UserPasswordPo;
import cn.fudges.user.service.UserPasswordService;
import cn.fudges.user.entity.UserPassword;
import cn.fudges.user.dao.po.UserPasswordPoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserPasswordServiceImpl extends ServiceImpl<UserPasswordPoMapper, UserPasswordPo> implements UserPasswordService {

}

