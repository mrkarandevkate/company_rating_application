package com.fqts.api_gateway;

import com.fqts.api_gateway.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {
    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CustomUserDetailsService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return webClient.get()
                .uri("user/loaduser/" + username)
                .retrieve()
                .bodyToMono(User.class)
                .doOnNext(user -> logger.info("Fetched User from API: {}", user))
                .map(user -> (UserDetails) new CustomUserDetails(user)) // ✅ Explicit Cast
                .doOnSuccess(userDetails -> logger.info("Mapped UserDetails: {}", userDetails))
                .onErrorResume(e -> {
                    logger.error("Error fetching user: {}", e.getMessage());
                    return Mono.error(new RuntimeException("User service is unavailable", e));
                });
    }

}
