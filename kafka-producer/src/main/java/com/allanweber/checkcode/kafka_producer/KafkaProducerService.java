package com.allanweber.checkcode.kafka_producer;

import com.allanweber.checkcode.common.dto.CorrelationId;
import com.allanweber.checkcode.common.dto.Message;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class KafkaProducerService<T> {

    private final KafkaProducer<String, T> producer;

    public KafkaProducerService(List<String> bootstrapServers) {
        this.producer = new KafkaProducer<>(properties(bootstrapServers));
    }

    private Map<String, Object> properties(List<String> bootstrapServers) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return props;
    }

    public void send(String topic, String key, T payload) throws ExecutionException, InterruptedException {
        var future = sendAsync(topic, key, payload);
        future.get();
    }

    public Future<RecordMetadata> sendAsync(String topic, String key, T payload) {
        var record = new ProducerRecord<>(topic, key, payload);
        return producer.send(record, (data, ex) -> {
            if(ex != null){
                log.error("Error to send message to topic {}: {}", topic, ex.getMessage(), ex);
            } else {
                String message = System.lineSeparator() +
                        "New message sent: " + System.lineSeparator() +
                        "-> Topic: " + data.topic() + System.lineSeparator() +
                        "-> Partition: " + data.partition() + System.lineSeparator() +
                        "-> Offset: " + data.offset() + System.lineSeparator() +
                        "-> Timestamp: " + data.timestamp() + System.lineSeparator();
                log.info(message);
            }
        });
    }
}
