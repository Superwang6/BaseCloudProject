package cn.fudges.authority.annotation;

import cn.fudges.authority.enums.Action;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String value() default "";
    String[] values() default "";
    Action action() default Action.NORMAL;
}
