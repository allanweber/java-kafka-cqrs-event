package com.allanweber.budget.kafka_producer;

import com.allanweber.budget.common.dto.CorrelationId;
import com.allanweber.budget.common.dto.Message;
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

public class KafkaProducerService<T> {

    private final KafkaProducer<String, Message<T>> producer;

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
        var value = new Message<>(new CorrelationId(), payload);
        var record = new ProducerRecord<>(topic, key, value);
        return producer.send(record);
    }
}
