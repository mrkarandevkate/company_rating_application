package com.fqts.api_gateway.config;

import com.fqts.api_gateway.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {
    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationManager.class);
    private final JwtUtils jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JWTAuthenticationManager(JwtUtils jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        logger.info("Authenticating token: {}", token);

        if (jwtUtil.isTokenExpired(token)) {
            logger.error("JWT Token is expired.");
            return Mono.empty();
        }

        String username = jwtUtil.extractUsername(token);
        List<SimpleGrantedAuthority> authorities = jwtUtil.extractRoles(token);

        logger.info("Extracted username: {} with roles: {}", username, authorities);

        return userDetailsService.findByUsername(username)
                .map(userDetails -> {
                    logger.info("User authenticated: {}", userDetails.getUsername());
                    return (Authentication) new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
                })
                .doOnError(error -> logger.error("Error authenticating user: {}", error.getMessage()));
    }

}
