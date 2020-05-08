package com.allanweber.checkcode.query.github.routes;

import com.allanweber.checkcode.query.github.repository.ReportHandlers;
import com.allanweber.checkcode.query.github.user.UserHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GitRoutes {

    @Bean
    public RouterFunction<?> userRoutes(UserHandlers userHandlers, ReportHandlers reportHandlers) {
        return nest(path("/github"),
                route(GET("/users").and(accept(APPLICATION_JSON)), userHandlers::getUsers)
                        .and(route(GET("/reports").and(accept(APPLICATION_JSON)), reportHandlers::getReports))
                        .and(route(GET("/reports/{id}").and(accept(APPLICATION_JSON)), reportHandlers::getReport))
                        .and(route(GET("/reports/{id}/repositories").and(accept(APPLICATION_JSON)), reportHandlers::getRepositories))
        );
    }
}
