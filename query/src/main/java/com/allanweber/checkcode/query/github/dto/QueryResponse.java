package com.allanweber.checkcode.query.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class QueryResponse<T> {

    private Integer totalCount;

    private T[] items;

    @JsonCreator

    public QueryResponse(@JsonProperty("total_count") Integer totalCount, @JsonProperty("items") T[] items) {
        this.totalCount = totalCount;
        this.items = items;
    }
}
