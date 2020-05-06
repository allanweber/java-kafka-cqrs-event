package com.allanweber.checkcode.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)
@EnableWebFlux
public class CheckCodeQueryApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CheckCodeQueryApplication.class, args);
    }

}
