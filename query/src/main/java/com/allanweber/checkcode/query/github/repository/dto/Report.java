package com.allanweber.checkcode.query.github.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Report {

    private String user;

    private String provider;
}
