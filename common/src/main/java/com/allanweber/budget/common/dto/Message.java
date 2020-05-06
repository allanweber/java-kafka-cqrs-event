package com.allanweber.budget.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Message<T> {

    private final CorrelationId id;
    private final T payload;

    @JsonCreator
    public Message(@JsonProperty("id") CorrelationId id, @JsonProperty("payload") T payload) {
        this.id = id;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", payload=" + payload +
                '}';
    }
}
