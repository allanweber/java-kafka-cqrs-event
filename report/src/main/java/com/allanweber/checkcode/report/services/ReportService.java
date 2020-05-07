package com.allanweber.checkcode.report.services;

import com.allanweber.checkcode.common.dto.ReportProperties;
import com.allanweber.checkcode.common.dto.RepositoryProperties;
import com.allanweber.checkcode.github.services.GitHubClient;
import com.allanweber.checkcode.report.mapper.RepositoryMapper;
import com.allanweber.checkcode.report.repositories.GitRepository;
import com.allanweber.checkcode.report.repositories.Languages;
import com.allanweber.checkcode.report.repositories.LanguagesEntityRepository;
import com.allanweber.checkcode.report.repositories.ReportEntity;
import com.allanweber.checkcode.report.repositories.ReportRepository;
import com.allanweber.checkcode.report.repositories.RepositoryLanguagesEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReportService {

    private final GitHubClient gitHubClient;
    private final ReportRepository reportRepository;
    private final LanguagesEntityRepository languagesEntityRepository;
    private final KafkaService kafkaService;
    private final RepositoryMapper mapper = Mappers.getMapper(RepositoryMapper.class);

    public void findRepositories(@NotNull ReportProperties properties) {

        Mono<List<GitRepository>> gitRepositories = gitHubClient.repositories(properties.getUser())
                .switchIfEmpty(Mono.error(new Exception(String.format("Not found any repository for the user %s", properties.getUser()))))
                .map(mapper::toGitRepository)
                .flatMap(repos -> enqueueRepositoryReport(properties.getId(), repos));

        Mono<ReportEntity> entity = reportRepository.findById(properties.getId());

        Mono.zip(gitRepositories, entity)
                .map(tuples -> tuples.getT2().addRepositories(tuples.getT1()))
                .flatMap(reportRepository::save)
                .doOnError(error -> log.error("Error to read github repositories.", error))
                .subscribe(result -> log.info("Updated Report entity {} with {} repositories.", result.getId(), result.getRepositories().size()));
    }

    private Mono<List<GitRepository>> enqueueRepositoryReport(String reportId, List<GitRepository> gitRepositories) {

        List<RepositoryProperties> repositoryProperties = mapper.toRepositoryProperties(reportId, gitRepositories);

        kafkaService.enqueueRepositoryReport(repositoryProperties);
        return Mono.just(gitRepositories);
    }

    public void processRepository(RepositoryProperties repository) {
        reportRepository.findById(repository.getReportId())
                .flatMap(entity -> gitHubClient.getRepositoryLanguages(entity.getUser(), repository.getName()))
                .flatMap(languages -> this.processLanguages(repository, languages.getLanguages()))
                .subscribe(languages -> log.info("New Languages Collection {}", languages.getLanguages()));
    }

    private Mono<RepositoryLanguagesEntity> processLanguages(RepositoryProperties repository, Map<String, Long> mapLanguages) {
        long totalSize = mapLanguages.values().stream().mapToLong(n -> n).sum();

        if(totalSize > 0) {
            List<Languages> languages = mapLanguages
                    .entrySet()
                    .stream()
                    .map(entry -> new Languages(entry.getKey(), entry.getValue(), BigDecimal.valueOf((entry.getValue() * 100) / totalSize)))
                    .collect(Collectors.toList());

            RepositoryLanguagesEntity entity =
                    new RepositoryLanguagesEntity(repository.getId(), repository.getReportId(), repository.getName(), languages);

            return languagesEntityRepository.save(entity);
        }

        log.warn("## Empty language repository {} ", repository.getName());
        return Mono.empty();
    }
}
