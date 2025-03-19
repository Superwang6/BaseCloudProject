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
 * 用户基础信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("user_base")
public class UserBasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 手机号
     */
    @TableField("mobile_phone")
    private String mobilePhone;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 实名状态，0-否，1-是
     */
    @TableField("auth_status")
    private Integer authStatus;

    /**
     * 身份证号码
     */
    @TableField("id_num")
    private String idNum;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 状态，0-正常，1-注销，2-禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 所属租户
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 平台，0-c端，1-b端
     */
    @TableField("platform")
    private Integer platform;
}
