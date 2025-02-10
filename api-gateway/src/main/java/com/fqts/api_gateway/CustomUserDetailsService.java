package com.fqts.api_gateway;
import com.fqts.api_gateway.CustomUserDetails;
import com.fqts.api_gateway.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {

    private final WebClient webClient;

    @Autowired
    public CustomUserDetailsService(WebClient webClient,
                                    ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.webClient = webClient;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return webClient.get()
                .uri("user/loaduser/" + username)
                .retrieve()
                .bodyToMono(User.class)
                .map(user -> (UserDetails) new CustomUserDetails(user))  // Cast here
                .doOnSuccess(userDetails -> System.out.println("Fetched UserDetails: " + userDetails))  // Log fetched data
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.error(new RuntimeException("User service is unavailable", e));
                });
    }
}
