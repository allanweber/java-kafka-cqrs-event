package com.allanweber.checkcode.command.github.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReportRepository extends ReactiveMongoRepository<ReportEntity, String> {
}
