package com.allanweber.checkcode.report.listeners;

import com.allanweber.checkcode.common.dto.ReportProperties;
import com.allanweber.checkcode.report.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportListener {

    private final ReportService reportService;

    @KafkaListener(topics = "REPORT_START")
    public void listen(ReportProperties properties) {
        reportService.findRepositories(properties);
    }
}
