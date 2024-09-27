package cn.fudges.oauth2.dao;

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
public class AuthOpenUserDao extends ServiceImpl<AuthOpenUserPoMapper, AuthOpenUser> {

}
