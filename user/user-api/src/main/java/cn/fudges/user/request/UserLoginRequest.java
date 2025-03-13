package cn.fudges.user.request;

import cn.fudges.baseapi.request.RequestEntity;
import lombok.Data;

/**
 * @author 王平远
 * @since 2024/9/29
 */
@Data
public class UserLoginRequest extends RequestEntity {

    private String username;

    private String password;
}
