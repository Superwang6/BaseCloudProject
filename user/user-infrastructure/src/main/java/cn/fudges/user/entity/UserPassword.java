package cn.fudges.user.entity;

import cn.fudges.user.entity.po.UserPasswordPo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 用户密码信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-10-09
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPassword extends UserPasswordPo {

}
