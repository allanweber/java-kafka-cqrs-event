package com.allanweber.checkcode.command.github.services;

import com.allanweber.checkcode.common.dto.ReportProperties;
import com.allanweber.checkcode.kafka_producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaService {

    private final KafkaProducerService<ReportProperties> producer;

    public void sendReportRequest(ReportProperties report) throws ExecutionException, InterruptedException {
        producer.send("REPORT_START", UUID.randomUUID().toString(), report);
    }
}
