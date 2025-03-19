package cn.fudges.role.response;

import java.io.Serializable;
import cn.fudges.baseapi.response.ResponseEntity;
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
public class RoleUserResponse extends ResponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;
}
