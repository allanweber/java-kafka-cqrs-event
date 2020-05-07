package com.allanweber.checkcode.report.repositories;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GitRepository {

    private Long id;

    private String name;

    private String description;

    private Boolean fork;

    private String language;
}
