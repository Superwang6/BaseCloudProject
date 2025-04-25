package cn.fudges.authority.client.inner;

import cn.fudges.authority.model.PermissionModel;

import java.util.List;
import java.util.Map;

/**
 * @author 王平远
 * @since 2025/4/20
 */

public interface AuthorityCacheClient {

    void put(String applicationId, Map<String, List<PermissionModel>> pathPermissionMap);

    Map<String, List<PermissionModel>> get(String applicationId);
}
