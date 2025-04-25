package cn.fudges.authority.enums;

/**
 * @author 王平远
 * @since 2025/4/10
 */
public enum Action {
    /** 正常（默认） */
    NORMAL("0", "执行验证"),

    /** 跳过 */
    SKIP("1", "跳过验证");

    /** 主键 */
    private final String key;

    /** 描述 */
    private final String desc;

    Action(final String key, final String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
