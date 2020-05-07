package com.allanweber.checkcode.command.github.services;

import com.allanweber.checkcode.command.github.repositories.ReportEntity;
import com.allanweber.checkcode.command.github.repositories.ReportRepository;
import com.allanweber.checkcode.command.mapper.ReportMapper;
import com.allanweber.checkcode.common.dto.ReportProperties;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final KafkaService kafkaService;

    private final ReportMapper mapper = Mappers.getMapper(ReportMapper.class);

    public Mono<ReportProperties> startReport(@NotBlank String user) {

        String provider = "GITHUB";
        ReportEntity entity = new ReportEntity(user, provider);

        return reportRepository.insert(entity)
                .map(mapper::fromEntity)
                .map(properties -> {
                    try {
                        kafkaService.sendReportRequest(properties);
                    } catch (ExecutionException | InterruptedException e) {
                       throw new HttpClientErrorException(INTERNAL_SERVER_ERROR, e.getMessage());
                    }
                    return properties;
                });
    }

}
