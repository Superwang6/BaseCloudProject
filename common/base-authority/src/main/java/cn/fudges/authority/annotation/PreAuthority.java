package cn.fudges.authority.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthority {
    String value() default "";
}
