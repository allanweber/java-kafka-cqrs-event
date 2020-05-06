package com.allanweber.checkcode.command.account.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class AccountRequest {

    @NotBlank
    private final String name;

    @NotNull
    @Min(0)
    private final BigDecimal initialBalance;

    @JsonCreator
    public AccountRequest(@JsonProperty("name") String name, @JsonProperty("initialBalance") BigDecimal initialBalance){
        this.name = name;
        this.initialBalance = initialBalance;
    }
}
