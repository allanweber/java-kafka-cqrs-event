package com.allanweber.checkcode.query.github.repository.services;

import com.allanweber.checkcode.query.github.repository.dto.Report;
import com.allanweber.checkcode.query.github.repository.dto.RepositoryLanguages;
import com.allanweber.checkcode.query.github.repository.mapper.ReportMapper;
import com.allanweber.checkcode.query.github.repository.repositories.ReportRepository;
import com.allanweber.checkcode.query.github.repository.repositories.RepositoryLanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final RepositoryLanguagesRepository repositoryLanguagesRepository;

    private final ReportMapper mapper = Mappers.getMapper(ReportMapper.class);

    public Flux<Report> getReports() {
        return reportRepository.findAll()
                .map(mapper::fromEntity);
    }

    public Mono<Report> getReport(String reportId) {
        return reportRepository.findById(reportId)
                .switchIfEmpty(Mono.error(new HttpClientErrorException(NOT_FOUND, String.format("Report %s not found", reportId))))
                .map(mapper::fromEntity);
    }

    public Flux<RepositoryLanguages> getRepositoryLanguages(String reportId) {
        return repositoryLanguagesRepository.findByReportId(reportId)
                .map(mapper::fromEntity);
    }
}
