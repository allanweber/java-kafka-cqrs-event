package com.allanweber.checkcode.github.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class GitHubWebClient {

    private final WebClient client;

    public GitHubWebClient() {
        this.client = buildGitHubWebClient();
    }

    public WebClient getClient() {
        return client;
    }

    private WebClient buildGitHubWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .filter(logRequest())
                .filter(errorResponse())
                .defaultHeader("Authorization", "Bearer 45dcaa1f931131bb5881df17b90f065b5ba7eae8")
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction errorResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                clientResponse.bodyToMono(String.class)
                        .map(ex -> Mono.error(new HttpClientErrorException(clientResponse.statusCode(), ex)));
            }
            return Mono.just(clientResponse);
        });
    }
}
