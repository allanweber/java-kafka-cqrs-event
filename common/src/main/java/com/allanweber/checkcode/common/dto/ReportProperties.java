package com.allanweber.checkcode.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportProperties {

    private String id;

    private String user;

    private String provider;
}
