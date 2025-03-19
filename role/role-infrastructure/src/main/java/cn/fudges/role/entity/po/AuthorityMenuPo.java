package cn.fudges.role.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 菜单权限关联表
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("authority_menu")
public class AuthorityMenuPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限id
     */
    @TableField("authority_id")
    private Integer authorityId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private String menuId;
}
