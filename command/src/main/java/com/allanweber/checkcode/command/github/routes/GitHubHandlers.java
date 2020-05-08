package com.allanweber.checkcode.command.github.routes;

import com.allanweber.checkcode.command.github.dto.UserRequest;
import com.allanweber.checkcode.command.github.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

@RequiredArgsConstructor
@Component
public class GitHubHandlers {

    private final ReportService service;

    public Mono<ServerResponse> startNewReport(ServerRequest request) {
        return request
                .bodyToMono(UserRequest.class)
                .flatMap(service::startReport)
                .flatMap(report -> created(URI.create("/reports/" + report.getUser()))
                        .contentType(APPLICATION_JSON)
                        .bodyValue(report));
    }

}
