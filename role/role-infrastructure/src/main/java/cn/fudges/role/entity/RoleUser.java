package cn.fudges.role.entity;

import cn.fudges.role.entity.po.RoleUserPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 用户角色关联表
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
public class RoleUser extends RoleUserPo {

}
