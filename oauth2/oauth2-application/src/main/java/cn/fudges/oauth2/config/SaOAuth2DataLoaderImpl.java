package cn.fudges.oauth2.config;

import cn.fudges.oauth2.entity.AuthOauthClient;
import cn.fudges.oauth2.entity.AuthOpenUser;
import cn.fudges.oauth2.service.AuthOauthClientService;
import cn.fudges.oauth2.service.AuthOpenUserService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王平远
 * @since 2024/9/27
 */
//@Component
public class SaOAuth2DataLoaderImpl
//        implements SaOAuth2DataLoader
{
//    @Autowired
//    AuthOauthClientService authOauthClientService;
//
//    @Autowired
//    AuthOpenUserService authOpenUserService;
//
//    @Override
//    public SaClientModel getClientModel(String clientId) {
//        QueryWrapper<AuthOauthClient> wrapper = new QueryWrapper<>();
//        wrapper.eq("client_id", clientId);
//        wrapper.eq("status", 0);
//        List<AuthOauthClient> oauthList = authOauthClientService.list(wrapper);
//        if(CollUtil.isNotEmpty(oauthList)) {
//            AuthOauthClient authOauth = oauthList.get(0);
//            return new SaClientModel()
//                    .setClientId(authOauth.getClientId())    // client id
//                    .setClientSecret(authOauth.getClientSecret())    // client 秘钥
//                    .addAllowRedirectUris(authOauth.getAllowRedirectUri().split(","))    // 所有允许授权的 url
//                    .addContractScopes(authOauth.getContractScopes().split(","))    // 所有签约的权限
//                    .addAllowGrantTypes(     // 所有允许的授权模式
//                            authOauth.getAllowGrantType().split(",")
//                    );
//        }
//        return null;
//    }
//
//    @Override
//    public String getOpenid(String clientId, Object loginId) {
//        QueryWrapper<AuthOpenUser> wrapper = new QueryWrapper<>();
//        wrapper.eq("client_id",clientId);
//        wrapper.eq("user_id", loginId);
//        wrapper.eq("status",0);
//        List<AuthOpenUser> list = authOpenUserService.list(wrapper);
//        if(CollUtil.isNotEmpty(list)) {
//            return list.get(0).getOpenId();
//        }
//        return null;
//    }
}
