package cn.fudges.oauth2.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Getter
@Setter
@TableName("auth_open_user")
public class AuthOpenUserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应auth_client的主键
     */
    @TableField("client_id")
    private String clientId;

    /**
     * 对应用户表的主键
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 对外的open_id
     */
    @TableField("open_id")
    private String openId;

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
     * 状态，0-正常，1-注销，2-禁用
     */
    @TableField("status")
    private Integer status;
}
