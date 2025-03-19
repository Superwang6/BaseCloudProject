package cn.fudges.role.entity;

import cn.fudges.role.entity.po.RolePo;
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
public class Role extends RolePo {

}
