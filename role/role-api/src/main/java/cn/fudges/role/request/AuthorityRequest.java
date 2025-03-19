package cn.fudges.role.request;

import java.io.Serializable;
import cn.fudges.baseapi.request.RequestEntity;
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
public class AuthorityRequest extends RequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 是否是叶子节点，0-否，1-是
     */
    private Integer isLeaf;

    /**
     * 是否可见，0-否，1-是
     */
    private Integer isVisiable;
}
