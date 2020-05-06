package com.allanweber.checkcode.query.github.user.routes;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class UserRoutes {

    private final UserHandlers userHandler;

    @Bean
    public RouterFunction<?> routes() {
        return nest(path("/github/users"),
                        route(GET("/"), userHandler::getUsers));
    }
}
