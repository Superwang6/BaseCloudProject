package cn.fudges.role.entity;

import cn.fudges.role.entity.po.AuthorityPo;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Authority extends AuthorityPo {

}
