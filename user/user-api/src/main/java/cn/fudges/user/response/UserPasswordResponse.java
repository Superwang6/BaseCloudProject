package cn.fudges.user.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import cn.fudges.baseapi.response.ResponseEntity;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户密码信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserPasswordResponse extends ResponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 登录随机盐
     */
    private String loginSalt;

    /**
     * 密码长度
     */
    private Integer passwordLength;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;
}
