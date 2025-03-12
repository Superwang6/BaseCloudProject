package cn.fudges.gatewayweb.service.impl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.common.utils.AssertUtils;
import cn.fudges.gatewayweb.mode.UserDetail;
import cn.fudges.gatewayweb.service.UserService;
import cn.fudges.user.response.UserBaseResponse;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author 王平远
 * @since 2025/3/12
 */
@Service
public class UserServiceImpl implements UserService {

    private final WebClient webClient;

    private static final String USER_BASE_URL = "http://user-service";

    public UserServiceImpl(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(USER_BASE_URL).build();
    }


    @Override
    public Mono<UserDetails> queryUserByUsernameReactive(String username) {
        return webClient.get()
                .uri("/user/user-base/{username}", username)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultResponse<UserBaseResponse>>() {})
                .flatMap(userResponse -> {
                    AssertUtils.isSuccess(userResponse);
                    UserDetail userDetail = BeanUtil.copyProperties(userResponse.getData(), UserDetail.class);
                    userDetail.setName(userResponse.getData().getUserName());
                    if (ObjectUtil.isAllNotEmpty(userResponse.getData(), userResponse.getData().getUserPassword())) {
                        userDetail.setPassword(userResponse.getData().getUserPassword().getLoginPassword());
                    }
                    return Mono.just(userDetail);
                });
    }
}
