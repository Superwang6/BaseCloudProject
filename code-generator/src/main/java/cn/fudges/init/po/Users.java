package cn.fudges.init.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangpeiyuan
 * @since 2024-08-16
 */
@Getter
@Setter
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * username
     */
    @TableId("username")
    private String username;

    /**
     * password
     */
    @TableField("password")
    private String password;

    /**
     * enabled
     */
    @TableField("enabled")
    private Boolean enabled;
}
