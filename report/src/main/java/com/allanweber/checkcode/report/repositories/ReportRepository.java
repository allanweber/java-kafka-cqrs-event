package com.allanweber.checkcode.report.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReportRepository extends ReactiveMongoRepository<ReportEntity, String> {
}
