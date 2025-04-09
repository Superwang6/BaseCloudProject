package cn.fudges.user.controller.apiImpl;

import cn.fudges.common.result.ResultResponse;
import cn.fudges.user.api.UserBaseApi;
import cn.fudges.user.entity.UserBase;
import cn.fudges.user.entity.UserPassword;
import cn.fudges.user.response.UserBaseResponse;
import cn.hutool.core.bean.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author 王平远
 * @since 2024/10/9
 */
@SpringBootTest
class UserBaseApiImplTest {

    @Autowired
    UserBaseApi userBaseApi;

}