package cn.fudges.user.entity;

import cn.fudges.user.entity.po.UserBasePo;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户基础信息
 * </p>
 *
 * @author wangpingyuan
 * @since 2024-09-27
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserBase extends UserBasePo {

    private UserPassword userPassword;
}
