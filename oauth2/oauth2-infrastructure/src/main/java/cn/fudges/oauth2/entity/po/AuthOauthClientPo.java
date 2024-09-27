package cn.fudges.oauth2.entity.po;

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
 * @since 2024-09-27
 */
@Getter
@Setter
@TableName("auth_oauth_client")
public class AuthOauthClientPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @TableId("client_id")
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

    /**
     * 允许跳转的url，逗号分割
     */
    @TableField("allow_redirect_uri")
    private String allowRedirectUri;

    /**
     * 签约的权限，逗号分割
     */
    @TableField("contract_scopes")
    private String contractScopes;

    /**
     * 状态，0-正常，1-禁用，2-删除
     */
    @TableField("status")
    private Integer status;
}
