package com.allanweber.checkcode.github.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Languages {

    private Map<String, Long> languages = new HashMap<>();

    @JsonAnySetter
    public void setLanguages(String key, Long value) {
        languages.put(key, value);
    }
}
