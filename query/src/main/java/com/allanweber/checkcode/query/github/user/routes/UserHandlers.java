package com.allanweber.checkcode.query.github.user.routes;

import com.allanweber.checkcode.query.github.dto.User;
import com.allanweber.checkcode.query.github.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Component
public class UserHandlers {

    private final UserService userService;

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        String query = request.queryParam("query")
                .map(String::toLowerCase)
                .map(String::trim)
                .orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Invalid user query"));

         return ok().body(userService.queryUsers(query), User.class);
    }
}
