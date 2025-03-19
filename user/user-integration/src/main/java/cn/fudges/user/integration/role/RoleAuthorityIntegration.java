package cn.fudges.user.integration.role;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.common.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王平远
 * @since 2025/3/17
 */
@Service
@RequiredArgsConstructor
public class RoleAuthorityIntegration {

//    private final RoleAuthorityApi roleAuthorityApi;

    public List<Integer> queryAuthorityIdListByUserId(Long userId) {
//        ResultResponse<List<Integer>> response =  roleAuthorityApi.queryAuthorityIdListByUserId(userId);
//        AssertUtils.isSuccess(response);
//        return response.getData();
        return null;
    }
}
