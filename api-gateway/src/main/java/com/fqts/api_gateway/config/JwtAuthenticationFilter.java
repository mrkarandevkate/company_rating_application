package com.fqts.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {
    private final JWTAuthenticationManager authenticationManager;
    private final ServerAuthenticationConverter authenticationConverter;
    private final ServerSecurityContextRepository securityContextRepository = new WebSessionServerSecurityContextRepository()   ;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JWTAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.authenticationConverter = exchange -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                logger.info("Extracted Token: {}", token);
                return Mono.just(new UsernamePasswordAuthenticationToken(token, token));
            }
            logger.warn("No Bearer token found in request");
            return Mono.empty();
        };
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return authenticationConverter.convert(exchange)
                .flatMap(authentication -> {
                    logger.info("Authenticating token...");
                    return authenticationManager.authenticate(authentication);
                })
                .flatMap(auth -> {
                    SecurityContext context = new SecurityContextImpl(auth);
                    return securityContextRepository.save(exchange, context)
                            .then(Mono.defer(() -> {
                                logger.info("Successfully authenticated user: {}", auth.getName());
                                return chain.filter(exchange);
                            }));
                })
                .switchIfEmpty(Mono.fromRunnable(() -> logger.warn("Authentication failed, proceeding without authentication"))
                        .then(chain.filter(exchange)));
    }
}
