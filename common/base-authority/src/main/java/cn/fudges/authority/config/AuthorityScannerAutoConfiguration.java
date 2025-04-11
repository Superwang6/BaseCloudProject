package cn.fudges.authority.config;

import cn.fudges.authority.annotation.Login;
import cn.fudges.authority.annotation.PreAuthority;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王平远
 * @since 2025/4/10
 */
@Configuration
@ConditionalOnClass({Login.class, PreAuthority.class})
public class AuthorityScannerAutoConfiguration {

}
