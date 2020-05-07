package com.allanweber.checkcode.query.github.repository.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RepositoryLanguagesRepository extends ReactiveMongoRepository<RepositoryLanguagesEntity, Long> {

    Flux<RepositoryLanguagesEntity> findByReportId(String reportId);
}
