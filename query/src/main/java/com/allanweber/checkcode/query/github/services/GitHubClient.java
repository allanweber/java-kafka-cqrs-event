package com.allanweber.checkcode.query.github.services;

import com.allanweber.checkcode.query.github.dto.QueryResponse;
import com.allanweber.checkcode.query.github.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Service
public class GitHubClient {

    private final WebClient client;

    public Mono<QueryResponse<User>> queryUsers(@NotBlank String query) {
        return client
                .get()
                .uri(builder -> builder.path("search/users").queryParam("q", query).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<QueryResponse<User>>() {});
    }

}
