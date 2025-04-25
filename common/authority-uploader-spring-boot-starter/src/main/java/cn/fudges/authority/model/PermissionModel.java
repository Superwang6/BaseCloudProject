package cn.fudges.authority.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王平远
 * @since 2025/4/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionModel {

    private String applicationId;

    private String path;

    private String permission;
}
