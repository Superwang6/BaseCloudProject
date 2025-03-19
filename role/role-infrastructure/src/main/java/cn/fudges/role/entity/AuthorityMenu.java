package cn.fudges.role.entity;

import cn.fudges.role.entity.po.AuthorityMenuPo;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AuthorityMenu extends AuthorityMenuPo {

}
