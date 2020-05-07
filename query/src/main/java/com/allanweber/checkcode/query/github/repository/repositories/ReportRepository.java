package com.allanweber.checkcode.query.github.repository.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReportRepository extends ReactiveMongoRepository<ReportEntity, String> {
}
