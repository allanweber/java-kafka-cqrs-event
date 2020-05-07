package com.allanweber.checkcode.command.github.routes;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Configuration
public class GitHubRoutes {

    private final GitHubHandlers handler;

    @Bean
    public RouterFunction<?> routes() {
        return route(POST("/reports").and(accept(APPLICATION_JSON)), handler::startNewReport);
    }
}
