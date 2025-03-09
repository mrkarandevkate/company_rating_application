package com.fqts.api_gateway.controller;

import com.fqts.api_gateway.CustomUserDetails;
import com.fqts.api_gateway.CustomUserDetailsService;
import com.fqts.api_gateway.config.JwtUtils;
import com.fqts.api_gateway.entity.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody AuthRequest authRequest) {
        Logger logger = LoggerFactory.getLogger(getClass());
        System.out.println("Received email: " + authRequest.getEmail());

        return userDetailsService.findByUsername(authRequest.getEmail())
                .flatMap(userDetails -> {
                    logger.info("Fetched UserDetails: " + userDetails);

                    if (userDetails == null) {
                        logger.warn("User not found: " + authRequest.getEmail());
                        return Mono.error(new BadCredentialsException("Your Access has been Revoked kindly check with admin"));
                    }


                    if (passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
                        String role = userDetails.getAuthorities().stream()
                                .findFirst()
                                .map(GrantedAuthority::getAuthority)
                                .orElse("Null");
                        String token = jwtUtil.generateToken(authRequest.getEmail(), role);
                        logger.info("Generated token with role: {}", role);
                        Map<String, String> responseBody = new HashMap<>();
                        responseBody.put("token", token);
                        return Mono.just(ResponseEntity.ok(responseBody));
                    } else {
                        logger.warn("Invalid email or password for user: " + authRequest.getEmail());
                        return Mono.error(new BadCredentialsException("Invalid email or password"));
                    }
                })
                .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid email or password")))
                .onErrorResume(e -> {
                    logger.error("Error occurred during login", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage())));
                });
    }

}
