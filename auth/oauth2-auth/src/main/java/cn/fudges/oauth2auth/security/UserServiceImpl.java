package cn.fudges.oauth2auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 王平远
 * @since 2024/9/13
 */
//@Service
public class UserServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = new UserResponse();
        userResponse.setName("admin");
        userResponse.setPwd("{noop}" + "123456");
        return userResponse;
    }
}
