package cn.fudges.role.controller.apiImpl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.role.api.RoleAuthorityApi;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 王平远
 * @since 2025/3/17
 */
@SpringBootTest
class RoleAuthorityApiImplTest {

    @Autowired
    private RoleAuthorityApiImpl roleAuthorityApi;

    @Test
    void queryAuthorityIdListByUserId() {
        TimeInterval timer = DateUtil.timer();
        ResultResponse<List<Integer>> response = roleAuthorityApi.queryAuthorityIdListByUserId(1L);
        System.out.println(response);
        System.out.println(timer.intervalRestart());
    }
}