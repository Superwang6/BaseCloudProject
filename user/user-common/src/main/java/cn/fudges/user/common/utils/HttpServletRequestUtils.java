package cn.fudges.user.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 王平远
 * @since 2025/4/25
 */

public class HttpServletRequestUtils {

    public static String getHeader(String name){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        HttpServletRequest request = attrs.getRequest();
        return request.getHeader(name);
    }
}
