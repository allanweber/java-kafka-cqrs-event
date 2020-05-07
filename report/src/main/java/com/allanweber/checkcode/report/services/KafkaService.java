package com.allanweber.checkcode.report.services;

import com.allanweber.checkcode.common.dto.RepositoryProperties;
import com.allanweber.checkcode.kafka_producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaService {

    private final KafkaProducerService<RepositoryProperties> producer;

    public void enqueueRepositoryReport(List<RepositoryProperties> repositories) {
        repositories.forEach(repository -> {
            try {
                producer.send("REPORT_REPOSITORY", UUID.randomUUID().toString(), repository);
            } catch (ExecutionException | InterruptedException e) {
                log.error("Error to enqueue REPORT_REPOSITORY.", e);
            }
        });
    }
}
