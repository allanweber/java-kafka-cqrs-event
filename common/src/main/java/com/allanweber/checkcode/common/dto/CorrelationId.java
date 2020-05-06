package com.allanweber.checkcode.common.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CorrelationId {

    private final String id;

    public CorrelationId() {
        this.id = UUID.randomUUID().toString();
    }

    public CorrelationId(String title) {
        this.id = title + "(" + UUID.randomUUID().toString() + ")";
    }

    @Override
    public String toString() {
        return "CorrelationId{" +
                "id='" + id + '\'' +
                '}';
    }

    public CorrelationId continueWith(String title) {
        return new CorrelationId(id + "-" + title);
    }
}
