package com.allanweber.checkcode.report.listeners;

import com.allanweber.checkcode.common.dto.RepositoryProperties;
import com.allanweber.checkcode.report.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportRepositoryListener {

    private final ReportService reportService;

    @KafkaListener(topics = "REPORT_REPOSITORY")
    public void listen(RepositoryProperties repository) {
        reportService.processRepository(repository);
    }
}
