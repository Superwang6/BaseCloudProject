package cn.fudges.role.entity;

import cn.fudges.role.entity.po.RoleAuthorityPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 角色权限关联表
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
public class RoleAuthority extends RoleAuthorityPo {

}
