package cn.fudges.role.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-08-20
 */
@Getter
@Setter
@TableName("role")
public class RolePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("role_desc")
    private String roleDesc;

    /**
     * 注释111
     */
    @TableField("tyint")
    private Boolean tyint;

    /**
     * 注释2222
     */
    @TableField("ot")
    private Integer ot;

    @TableField("doub")
    private Long doub;

    @TableField("time")
    private LocalDateTime time;

    @TableField("dat")
    private LocalDate dat;

    @TableField("timestamp")
    private LocalDateTime timestamp;

    @TableField("text")
    private String text;
}
