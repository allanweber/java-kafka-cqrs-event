package com.allanweber.checkcode.report.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LanguagesEntityRepository extends ReactiveMongoRepository<RepositoryLanguagesEntity, Long> {
}
