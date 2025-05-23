package cn.fudges.user.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import cn.fudges.baseapi.request.RequestEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 用户基础信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserBaseRequest extends RequestEntity implements Serializable {

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

    /**
     * 平台，0-c端，1-b端
     */
    private Integer platform;

    /**
     * 密码
     */
    private String password;
}
