package cn.fudges.gatewayweb.mode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/10/9
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Data
public class UserDetail implements UserDetails, Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 实名状态，0-否，1-是
     */
    private Integer authStatus;

    /**
     * 身份证号码
     */
    private String idNum;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 状态，0-正常，1-注销，2-禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 所属租户
     */
    private Long tenantId;

    /**
     * 权限id列表
     */
    private List<Long> authorityIdList;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return AuthorityUtils.createAuthorityList(this.authorityIdList.stream().map(String::valueOf).collect(Collectors.toList()));
        return AuthorityUtils.createAuthorityList("query");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 0;
    }
}
