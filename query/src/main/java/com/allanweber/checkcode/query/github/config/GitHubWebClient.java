package com.allanweber.checkcode.query.github.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Configuration
@Slf4j
public class GitHubWebClient {

    @Bean
    public WebClient buildGitHubWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .filter(logRequest())
                .filter(errorResponse())
                .defaultHeader("Authorization", "Bearer 627b0ac5ae9ed6d6ea8659bdb08bdfa2586af0a2")
                .build();
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction errorResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                clientResponse.bodyToMono(String.class)
                        .flatMap(ex -> Mono.error(new HttpClientErrorException(INTERNAL_SERVER_ERROR, ex)));
            }
            return Mono.just(clientResponse);
        });
    }
}
