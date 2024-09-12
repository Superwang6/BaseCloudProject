package cn.fudges.user.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户基础信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-12
 */
@Getter
@Setter
public class UserBaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String userName;

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
}
