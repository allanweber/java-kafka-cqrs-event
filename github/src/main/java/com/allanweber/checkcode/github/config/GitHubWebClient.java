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
                .defaultHeader("Authorization", "Bearer aa4f3ad8a922ce6995e98d5b52d6959b62f08438")
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
