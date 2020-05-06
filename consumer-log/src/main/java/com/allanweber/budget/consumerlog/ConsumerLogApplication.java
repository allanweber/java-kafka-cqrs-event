package com.allanweber.budget.consumerlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;

@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)
public class ConsumerLogApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ConsumerLogApplication.class, args);
    }

}
