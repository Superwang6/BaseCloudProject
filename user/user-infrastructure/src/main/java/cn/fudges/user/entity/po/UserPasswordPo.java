package cn.fudges.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 用户密码信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-11-29
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("user_password")
public class UserPasswordPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 登录密码
     */
    @TableField("login_password")
    private String loginPassword;

    /**
     * 密码长度
     */
    @TableField("password_length")
    private Integer passwordLength;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;
}
