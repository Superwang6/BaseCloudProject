package cn.fudges.oauth2.service.impl;

import cn.fudges.user.api.UserBaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 王平远
 * @since 2024/10/9
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserBaseApi userBaseApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        ResultResponse<UserBaseResponse> userResponse = userBaseApi.queryUserByUsername(username);
//        AssertUtils.isSuccess(userResponse);
//        UserDetail userDetail = BeanUtil.copyProperties(userResponse.getData(), UserDetail.class);
//        userDetail.setName(userResponse.getData().getUserName());
//        if(ObjectUtil.isAllNotEmpty(userResponse.getData(), userResponse.getData().getUserPassword())) {
//            userDetail.setPassword(userResponse.getData().getUserPassword().getLoginPassword());
//        }
//        return userDetail;
        return null;
    }
}
