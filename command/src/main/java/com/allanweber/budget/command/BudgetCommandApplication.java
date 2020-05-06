package com.allanweber.budget.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)
@EnableWebFlux
public class BudgetCommandApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BudgetCommandApplication.class, args);
    }

}
