package cn.fudges.gatewayweb.utils;

import cn.fudges.gateway.common.enums.GatewayHttpHeader;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author 王平远
 * @since 2025/3/17
 */

public class GatewayHeaderUtils {

    public static String getMetaData(ServerWebExchange exchange, String key) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String metaDataStr = headers.getFirst(GatewayHttpHeader.META_DATA.getValue());
        if(!StrUtil.hasBlank(metaDataStr)) {
            JSONObject metaData = JSON.parseObject(metaDataStr);
            if(ObjectUtil.isNotNull(metaData) && StringUtils.hasText(key)) {
                return metaData.getString(key);
            }
        }
        return null;
    }
}
