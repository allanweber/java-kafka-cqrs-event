package com.allanweber.checkcode.query.github.user.services;

import com.allanweber.checkcode.query.github.dto.QueryResponse;
import com.allanweber.checkcode.query.github.services.GitHubClient;
import com.allanweber.checkcode.query.github.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final GitHubClient gitHubClient;

    public Mono<List<User>> queryUsers(@NotBlank String query) {
        return gitHubClient.queryUsers(query).map(QueryResponse::getItems).map(Arrays::asList);
    }
}
