package com.fqts.api_gateway.config;

import com.fqts.api_gateway.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {
    Logger logger = LoggerFactory.getLogger(JWTAuthenticationManager.class);
    private final JwtUtils jwtUtil;
    private final CustomUserDetailsService userService;

    public JWTAuthenticationManager(JwtUtils jwtUtil, CustomUserDetailsService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = jwtUtil.extractUserName(token);

        return userService.findByUsername(username)
                .map(userDetails -> {
                    if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

                        logger.info("Authenticated user: {}", username);
                        logger.info("Assigned roles: {}", authorities);

                        // Print each role separately
                        authorities.forEach(auth -> logger.info("Role: {}", auth.getAuthority()));

                        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
                    } else {
                        throw new AuthenticationException("Invalid JWT token") {};
                    }
                });
    }

    public ServerAuthenticationConverter authenticationConverter() {
        return new ServerAuthenticationConverter() {
            @Override
            public Mono<Authentication> convert(ServerWebExchange exchange) {
                String token = exchange.getRequest().getHeaders().getFirst("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                    return Mono.just(SecurityContextHolder.getContext().getAuthentication());
                }
                return Mono.empty();
            }
        };
    }
}