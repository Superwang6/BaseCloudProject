package cn.fudges.gatewayweb.service;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * @author 王平远
 * @since 2025/3/12
 */

public interface UserService {
    Mono<UserDetails> queryUserByUsernameReactive(String username);
}
