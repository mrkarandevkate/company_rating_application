package com.fqts.api_gateway.config;

import com.fqts.api_gateway.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {
    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationManager.class);
    private final JwtUtils jwtUtil;
    private final CustomUserDetailsService userService;

    public JWTAuthenticationManager(JwtUtils jwtUtil, CustomUserDetailsService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String username = jwtUtil.extractUserName(token);
        String role = jwtUtil.extractUserRole(token);

        return userService.findByUsername(username)
                .map(userDetails -> {
                    if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                        GrantedAuthority authority = new SimpleGrantedAuthority(role);
                        logger.info("Authenticated user: {}", username);
                        logger.info("Assigned role: {}", role);
                        return new UsernamePasswordAuthenticationToken(userDetails, token, List.of(authority));
                    }
                    throw new RuntimeException("Invalid JWT token");
                });
    }

}