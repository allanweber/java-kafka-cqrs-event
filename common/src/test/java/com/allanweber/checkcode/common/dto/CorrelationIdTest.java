package com.allanweber.checkcode.common.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CorrelationIdTest {

    @Test
    public void when_create_new_CorrelationId_then_generate_new_id() {
        CorrelationId correlationId = new CorrelationId();

        assertNotNull(correlationId.getId());
    }

    @Test
    public void when_create_new_CorrelationId_with_title_then_concatenate_title_and_new_id() {
        CorrelationId correlationId = new CorrelationId("any-title");

        assertTrue(correlationId.getId().contains("any-title"));
    }

    @Test
    public void when_continue_CorrelationId_then_concatenate_both_ids() {
        CorrelationId correlationId = new CorrelationId();
        CorrelationId correlationIdContinue = correlationId.continueWith("next-id");

        assertFalse(correlationId.getId().contains("next-id"));
        assertTrue(correlationIdContinue.getId().contains("next-id"));
    }
}