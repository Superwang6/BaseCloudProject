package cn.fudges.common.result;

/**
 * @author 王平远
 * @since 2024/8/20
 */

public enum ResultCodeEnum implements IResultCodeEnum {

    SUCCESS("0000", "请求成功"),
    BUSINESS_EXCEPTION("1000", "业务异常"),
    NO_LOGIN("1001", "未登录"),
    PARAM_ERROR("1002", "参数错误"),
    SYSTEM_ERROR("9999", "系统错误"),
    ;

    private final String code;

    private final String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
