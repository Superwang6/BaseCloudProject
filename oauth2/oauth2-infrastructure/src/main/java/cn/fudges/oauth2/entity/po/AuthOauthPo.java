package cn.fudges.oauth2.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * oauth2.0 的客户端表
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-25
 */
@Getter
@Setter
@TableName("auth_oauth")
public class AuthOauthPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户端id
     */
    @TableField("client_id")
    private String clientId;

    /**
     * 客户端密钥
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     * 允许的授权模式，逗号分割。authorization_code 授权码模式，refresh_token刷新授权码，password密码模式，client_credentials客户端模式，implicit简化模式
     */
    @TableField("allow_grant_type")
    private String allowGrantType;
}
