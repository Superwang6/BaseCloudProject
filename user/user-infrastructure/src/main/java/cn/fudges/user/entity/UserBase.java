package cn.fudges.user.entity;

import cn.fudges.user.entity.po.UserBasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * <p>
 * 用户基础信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBase extends UserBasePo {

    private UserPassword userPassword;

    private List<Integer> authorityIdList;
}
