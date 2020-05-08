package com.allanweber.checkcode.query.github.repository;

import com.allanweber.checkcode.query.github.repository.dto.Report;
import com.allanweber.checkcode.query.github.repository.dto.RepositoryLanguages;
import com.allanweber.checkcode.query.github.repository.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Component
public class ReportHandlers {

    private final ReportService reportService;

    public Mono<ServerResponse> getReports(ServerRequest request) {
        return ok().body(reportService.getReports(), Report.class);
    }

    public Mono<ServerResponse> getReport(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok().body(reportService.getReport(id), Report.class);
    }

    public Mono<ServerResponse> getRepositories(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok().body(reportService.getRepositoryLanguages(id), RepositoryLanguages.class);
    }
}
