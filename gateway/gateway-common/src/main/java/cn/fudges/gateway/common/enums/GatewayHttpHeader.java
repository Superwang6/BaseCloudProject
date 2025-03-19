package cn.fudges.gateway.common.enums;

/**
 * @author 王平远
 * @since 2025/3/17
 */
public enum GatewayHttpHeader {

    META_DATA("Meta-Data");

    private final String value;

    GatewayHttpHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
