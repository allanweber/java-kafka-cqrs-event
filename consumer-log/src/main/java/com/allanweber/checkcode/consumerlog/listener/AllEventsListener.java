package com.allanweber.checkcode.consumerlog.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AllEventsListener {

    @KafkaListener(topicPattern = ".*")
    public void listen(List<ConsumerRecord<String, Object>> records){
        StringBuilder message = new StringBuilder();
        log.info("Receiving {} records", records.size() );

        for (var record: records) {
            message.append("-------------------------------------------------------------------").append(System.lineSeparator())
                    .append("New Message on Topic: ").append(record.topic()).append(System.lineSeparator())
                    .append("-> Partition: ").append(record.partition()).append(System.lineSeparator())
                    .append("-> Offset: ").append(record.offset()).append(System.lineSeparator())
                    .append("-> Timestamp: ").append(record.timestamp()).append(System.lineSeparator())
                    .append("-> Key: ").append(record.key()).append(System.lineSeparator())
                    .append("-> Message Body: ").append(record.value()).append(System.lineSeparator());

            log.info(message.toString());
        }
    }
}
