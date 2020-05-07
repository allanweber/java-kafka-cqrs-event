package com.allanweber.checkcode.github.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Repository {

    private Long id;

    private String name;

    private String description;

    private Boolean fork;

    private String language;
}
