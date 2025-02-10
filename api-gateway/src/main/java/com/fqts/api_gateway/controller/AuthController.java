package com.fqts.api_gateway.controller;

import com.fqts.api_gateway.CustomUserDetailsService;
import com.fqts.api_gateway.config.JwtUtils;
import com.fqts.api_gateway.entity.AuthRequest;
import com.fqts.api_gateway.entity.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody AuthRequest authRequest) {
        Logger logger = LoggerFactory.getLogger(getClass());
        System.out.println("Received email: " + authRequest.getEmail()); // Debugging

        return userDetailsService.findByUsername(authRequest.getEmail())
                .flatMap(userDetails -> {
                    logger.info("Fetched UserDetails: " + userDetails);

                    if (authRequest.getPassword().equals(userDetails.getPassword())) {
                        String token = jwtUtil.generateToken(authRequest.getEmail()); // Use email
                        logger.info("Generated token: " + token); // Log generated token
                        return Mono.just(ResponseEntity.ok(token)); // Return the token
                    } else {
                        logger.warn("Invalid email or password for user: " + authRequest.getEmail());
                        return Mono.error(new BadCredentialsException("Invalid email or password"));
                    }
                })
                .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid email or password")))
                .onErrorResume(e -> {
                    logger.error("Error occurred during login", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()));
                });
    }

}
