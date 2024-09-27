package cn.fudges.oauth2.service.impl;

import cn.fudges.oauth2.service.AuthOpenUserService;
import cn.fudges.oauth2.entity.AuthOpenUser;
import cn.fudges.oauth2.dao.po.AuthOpenUserPoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Service
public class AuthOpenUserServiceImpl extends ServiceImpl<AuthOpenUserPoMapper, AuthOpenUser> implements AuthOpenUserService {

}

