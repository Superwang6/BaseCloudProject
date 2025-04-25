package cn.fudges.authority.scanner;

import cn.fudges.authority.annotation.Permission;
import cn.fudges.authority.client.inner.AuthorityCacheClient;
import cn.fudges.authority.config.ApplicationIdConfiguration;
import cn.fudges.authority.model.PermissionModel;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@RequiredArgsConstructor
public class PermissionScanner implements SmartInitializingSingleton {

    private Logger LOGGER = LoggerFactory.getLogger(PermissionScanner.class);

    private final RequestMappingInfoHandlerMapping handlerMapping;

    private final ApplicationIdConfiguration applicationIdConfiguration;

    private final AuthorityCacheClient cacheClient;

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, List<PermissionModel>> pathPermissionMap = new HashMap<>();

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        for (RequestMappingInfo key : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(key);

            // 获取类上的基础权限列表
            List<String> baseList = getClassAuthList(handlerMethod);

            // 获取方法上的权限列表
            List<String> authList = getMethodAuthList(handlerMethod);

            // 构建urlAuthMap
            List<String> allAuthList = new ArrayList<>();
            if(CollUtil.isNotEmpty(authList)) {
                allAuthList.addAll(authList);
            }
            if(CollUtil.isNotEmpty(baseList)) {
                allAuthList.addAll(baseList);
            }
            Set<String> pathList = new HashSet<>();
            if(CollUtil.isNotEmpty(allAuthList)) {
                PathPatternsRequestCondition pathPatternsCondition = key.getPathPatternsCondition();
                if(ObjectUtil.isNotNull(pathPatternsCondition)) {
                    Set<String> directPaths = pathPatternsCondition.getPatternValues();

                    Set<RequestMethod> methods = null;
                    RequestMethodsRequestCondition methodsCondition = key.getMethodsCondition();
                    if(ObjectUtil.isNotNull(methodsCondition)) {
                        methods = methodsCondition.getMethods();
                    }
                    if(CollUtil.isNotEmpty(methods)) {
                        for (RequestMethod method : methods) {
                            String name = method.name();
                            for (String path : directPaths) {
                                pathList.add(name + " " + path);
                            }
                        }
                    } else {
                        pathList.addAll(directPaths);
                    }
                }
            }
            if(CollUtil.isNotEmpty(pathList) && CollUtil.isNotEmpty(allAuthList)) {
                pathList.forEach(path -> {
                    List<PermissionModel> modelList = allAuthList.stream().map(permission -> {
                        PermissionModel permissionModel = new PermissionModel();
                        permissionModel.setPermission(permission);
                        permissionModel.setPath(path);
                        permissionModel.setApplicationId(applicationIdConfiguration.getPermissionApplicationId());
                        return permissionModel;
                    }).toList();
                    pathPermissionMap.put(path, modelList);
                });
            }
        }

        LOGGER.info("permission scanner finished. applicationId:{}, pathPermissionMap:{}", applicationIdConfiguration.getPermissionApplicationId(), JSON.toJSONString(pathPermissionMap));
        cacheClient.put(applicationIdConfiguration.getPermissionApplicationId(), pathPermissionMap);
    }

    private List<String> getClassAuthList(HandlerMethod handlerMethod) {
        Class<?> methodClass = handlerMethod.getBeanType();
        if(methodClass.isAnnotationPresent(Permission.class)) {
            String value = methodClass.getAnnotation(Permission.class).value();
            String[] values = methodClass.getAnnotation(Permission.class).values();
            List<String> authorityList = new ArrayList<>();
            if(StrUtil.isNotBlank(value)) {
                authorityList.add(value);
            }
            if(ArrayUtil.isNotEmpty(values)) {
                authorityList.addAll(Arrays.stream(values).filter(StrUtil::isNotBlank).toList());
            }
            return authorityList;
        }
        return null;
    }

    private List<String> getMethodAuthList(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Permission.class)) {
            List<String> authList = new ArrayList<>();
            String value = method.getAnnotation(Permission.class).value();
            String[] values = method.getAnnotation(Permission.class).values();
            if(StrUtil.isNotBlank(value)) {
                authList.add(value);
            }
            if(ArrayUtil.isNotEmpty(values)) {
                authList.addAll(Arrays.asList(values));
            }
            return authList;
        }
        return null;
    }
}
