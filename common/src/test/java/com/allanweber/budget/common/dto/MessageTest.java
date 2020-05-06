package com.allanweber.budget.common.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    public void create_new_message_with_CorrelationId_and_payload() {
        Message<String> message = new Message<>(new CorrelationId(), "any message");

        assertNotNull(message.getId());
        assertNotNull(message.getPayload());
    }

    @Test
    public void create_new_message_with_composed_CorrelationId_and_payload() {
        Message<String> message = new Message<>(new CorrelationId().continueWith("my-title"), "any message");

        assertNotNull(message.getId());
        assertNotNull(message.getPayload());
        assertTrue(message.getId().toString().contains("my-title"));
    }
}