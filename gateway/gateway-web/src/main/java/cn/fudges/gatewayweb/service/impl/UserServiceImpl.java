package cn.fudges.gatewayweb.service.impl;

import cn.fudges.authority.modes.UserDetail;
import cn.fudges.authority.service.UserService;
import cn.fudges.common.result.ResultResponse;
import cn.fudges.common.utils.AssertUtils;
import cn.fudges.user.request.UserBaseRequest;
import cn.fudges.user.response.UserBaseResponse;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
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
    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }

    @Override
    public Mono<UserDetails> queryUserByUsernameReactive(String username, Integer platform) {
        UserBaseRequest request = new UserBaseRequest();
        request.setUserName(username);
        request.setPlatform(platform);
        return webClient.post()
                .uri("/user/user-base/login")
                .bodyValue(JSON.toJSONString(request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultResponse<UserBaseResponse>>() {})
                .flatMap(userResponse -> {
                    AssertUtils.isSuccess(userResponse);
                    if(ObjectUtil.isNotNull(userResponse.getData())) {
                        UserDetail userDetail = BeanUtil.copyProperties(userResponse.getData(), UserDetail.class);
                        userDetail.setName(userResponse.getData().getUserName());
                        if (ObjectUtil.isAllNotEmpty(userResponse.getData(), userResponse.getData().getUserPassword())) {
                            userDetail.setPassword(userResponse.getData().getUserPassword().getLoginPassword());
                        }
                        return Mono.just(userDetail);
                    }
                    return Mono.empty();
                });
    }
}
