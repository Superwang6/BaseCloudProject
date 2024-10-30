package cn.fudges.oauth2.service.impl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.common.utils.AssertUtils;
import cn.fudges.oauth2.mode.UserDetail;
import cn.fudges.user.api.UserBaseApi;
import cn.fudges.user.response.UserBaseResponse;
import cn.hutool.core.bean.BeanUtil;
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
        ResultResponse<UserBaseResponse> userResponse = userBaseApi.queryUserByUsername(username);
        AssertUtils.isSuccess(userResponse);
        UserDetail userDetail = BeanUtil.copyProperties(userResponse, UserDetail.class);

        return userDetail;
    }
}
