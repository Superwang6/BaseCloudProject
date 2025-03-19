package cn.fudges.role.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("authority")
public class AuthorityPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 层级
     */
    @TableField("level")
    private Integer level;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 是否是叶子节点，0-否，1-是
     */
    @TableField("is_leaf")
    private Integer isLeaf;

    /**
     * 是否可见，0-否，1-是
     */
    @TableField("is_visible")
    private Integer isVisible;
}
