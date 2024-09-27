package cn.fudges.oauth2.service.impl;

import cn.fudges.oauth2.service.AuthOauthClientService;
import cn.fudges.oauth2.entity.AuthOauthClient;
import cn.fudges.oauth2.dao.po.AuthOauthClientPoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * oauth2.0 的客户端表 服务实现类
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Service
public class AuthOauthClientServiceImpl extends ServiceImpl<AuthOauthClientPoMapper, AuthOauthClient> implements AuthOauthClientService {

}

