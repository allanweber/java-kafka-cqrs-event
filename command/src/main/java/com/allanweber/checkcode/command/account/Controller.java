package com.allanweber.checkcode.command.account;

import com.allanweber.checkcode.command.account.dto.AccountRequest;
import com.allanweber.checkcode.kafka_producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/accounts")
@Validated
@RequiredArgsConstructor
public class Controller {

    private final KafkaProducerService<AccountRequest> producerService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid AccountRequest account) throws ExecutionException, InterruptedException {

        producerService.send("CHECK_ACCOUNT_CREATION", UUID.randomUUID().toString(), account);

        return ResponseEntity.ok().body("hihi");

    }

}
