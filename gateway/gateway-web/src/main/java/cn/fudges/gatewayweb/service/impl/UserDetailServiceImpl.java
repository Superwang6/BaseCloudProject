package cn.fudges.gatewayweb.service.impl;

import cn.fudges.gatewayweb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author 王平远
 * @since 2024/10/9
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements ReactiveUserDetailsService {

    private final UserService userService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userService.queryUserByUsernameReactive(username);
    }
}
