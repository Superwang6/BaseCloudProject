package cn.fudges.gatewayweb.security.filter;

import cn.fudges.gatewayweb.security.converter.JsonAuthenticationConverter;
import cn.fudges.gatewayweb.security.handler.JsonAuthenticationFailureHandler;
import cn.fudges.gatewayweb.security.handler.JsonAuthenticationSuccessHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.*;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author 王平远
 * @since 2025/3/17
 */

public class LoginAuthenticationWebFilter implements WebFilter {

    private static final Log logger = LogFactory.getLog(LoginAuthenticationWebFilter.class);

    private final ReactiveAuthenticationManagerResolver<ServerWebExchange> authenticationManagerResolver;

    private final ServerWebExchangeMatcher backendServerWebExchangeMatcher = new PathPatternParserServerWebExchangeMatcher("/admin/login/password-login");
    private final ServerWebExchangeMatcher webServerWebExchangeMatcher = new PathPatternParserServerWebExchangeMatcher("/login/passwordLogin");

    private final ServerAuthenticationConverter authenticationConverter = new JsonAuthenticationConverter();

    private final ServerSecurityContextRepository securityContextRepository = NoOpServerSecurityContextRepository
            .getInstance();

    private final ServerAuthenticationSuccessHandler authenticationSuccessHandler;
    private final ServerAuthenticationFailureHandler authenticationFailureHandler = new JsonAuthenticationFailureHandler();



    public LoginAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager, ServerAuthenticationSuccessHandler authenticationSuccessHandler) {
        Assert.notNull(authenticationManager, "authenticationManager cannot be null");
        this.authenticationManagerResolver = (request) -> Mono.just(authenticationManager);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    public LoginAuthenticationWebFilter(ReactiveAuthenticationManagerResolver<ServerWebExchange> authenticationManagerResolver, ServerAuthenticationSuccessHandler authenticationSuccessHandler) {
        Assert.notNull(authenticationManagerResolver, "authenticationResolverManager cannot be null");
        this.authenticationManagerResolver = authenticationManagerResolver;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Mono<ServerWebExchangeMatcher.MatchResult> backendMono = backendServerWebExchangeMatcher.matches(exchange)
                .filter(ServerWebExchangeMatcher.MatchResult::isMatch);
        Mono<ServerWebExchangeMatcher.MatchResult> webMono = webServerWebExchangeMatcher.matches(exchange)
                .filter(ServerWebExchangeMatcher.MatchResult::isMatch);
        return Mono.firstWithValue(backendMono, webMono)
                .flatMap((matchResult) -> this.authenticationConverter.convert(exchange))
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .flatMap((token) -> authenticate(exchange, chain, token))
                .onErrorResume(AuthenticationException.class, (ex) -> this.authenticationFailureHandler
                        .onAuthenticationFailure(new WebFilterExchange(exchange, chain), ex));
    }

    private Mono<Void> authenticate(ServerWebExchange exchange, WebFilterChain chain, Authentication token) {
        return this.authenticationManagerResolver.resolve(exchange)
                .flatMap((authenticationManager) -> authenticationManager.authenticate(token))
                .switchIfEmpty(Mono
                        .defer(() -> Mono.error(new IllegalStateException("No provider found for " + token.getClass()))))
                .flatMap(
                        (authentication) -> onAuthenticationSuccess(authentication, new WebFilterExchange(exchange, chain)))
                .doOnError(AuthenticationException.class,
                        (ex) -> logger.debug(LogMessage.format("Authentication failed: %s", ex.getMessage()), ex));
    }

    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        return this.securityContextRepository.save(exchange, securityContext)
                .then(this.authenticationSuccessHandler.onAuthenticationSuccess(webFilterExchange, authentication))
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
    }
}
