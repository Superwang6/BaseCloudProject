package cn.fudges.gatewayweb.security.token;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 王平远
 * @since 2025/3/17
 */
@Getter
public class FudgesUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Integer platform;

    public FudgesUsernamePasswordAuthenticationToken(Object principal, Object credentials, Integer platform) {
        super(principal, credentials);
        this.platform = platform;
    }

    public FudgesUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Integer platform) {
        super(principal, credentials, authorities);
        this.platform = platform;
    }
}
