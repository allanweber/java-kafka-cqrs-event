package com.allanweber.checkcode.github.services;

import com.allanweber.checkcode.github.config.GitHubWebClient;
import com.allanweber.checkcode.github.dto.Languages;
import com.allanweber.checkcode.github.dto.QueryResponse;
import com.allanweber.checkcode.github.dto.Repository;
import com.allanweber.checkcode.github.dto.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class GitHubClient {

    private final WebClient client;

    public GitHubClient() {
        this.client = new GitHubWebClient().getClient();
    }

    public Mono<QueryResponse<User>> queryUsers(@NotBlank String query) {
        return client
                .get()
                .uri(builder -> builder.path("search/users").queryParam("q", query).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<QueryResponse<User>>() {
                });
    }

    public Mono<List<Repository>> repositories(@NotBlank String user) {
        return client
                .get()
                .uri(builder -> builder.pathSegment("users", user, "repos").build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Repository>>() {
                });
    }

    public Mono<Languages> getRepositoryLanguages(String user, String repositoryName) {
        return client
                .get()
                .uri(builder -> builder.pathSegment("repos", user, repositoryName, "languages").build())
                .retrieve()
                .bodyToMono(Languages.class);
    }
}
