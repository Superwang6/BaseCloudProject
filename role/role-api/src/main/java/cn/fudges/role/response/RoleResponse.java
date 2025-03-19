package cn.fudges.role.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import cn.fudges.baseapi.response.ResponseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wangpingyuan
 * @since 2025-03-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleResponse extends ResponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 是否删除，0-否，1-是
     */
    private Integer isRemove;

    /**
     * 所属租户
     */
    private Long tenantId;

    /**
     * 是否超管，0-否，1-是。超管拥有所有权限，不需要绑定
     */
    private Integer isAdmin;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
