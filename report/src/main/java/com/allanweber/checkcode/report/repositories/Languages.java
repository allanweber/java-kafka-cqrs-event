package com.allanweber.checkcode.report.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Languages {

    private String name;

    private Long size;

    private BigDecimal percentage;
}
