package cn.fudges.authority.client.impl;

import cn.fudges.authority.client.inner.AuthorityCacheClient;
import cn.fudges.authority.model.PermissionModel;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.Map;

/**
 * @author 王平远
 * @since 2025/4/20
 */
@RequiredArgsConstructor
public class RedisCacheClient implements AuthorityCacheClient {

    private final RedissonClient redissonClient;

    private static final String CONCENTRATE_AUTHORIZATION_CACHE_PREFIX = "concentrate-authorization-cache:";


    @Override
    public void put(String applicationId, Map<String, List<PermissionModel>> pathPermissionMap) {
        RBucket<Map<String, List<PermissionModel>>> bucket = redissonClient.getBucket(CONCENTRATE_AUTHORIZATION_CACHE_PREFIX + applicationId);
        bucket.set(pathPermissionMap);
    }

    @Override
    public Map<String, List<PermissionModel>> get(String applicationId) {
        RBucket<Map<String, List<PermissionModel>>> bucket = redissonClient.getBucket(CONCENTRATE_AUTHORIZATION_CACHE_PREFIX + applicationId);
        return bucket.get();
    }
}
