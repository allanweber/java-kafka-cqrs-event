package com.allanweber.checkcode.query.github.repository.repositories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "report")
@Getter
@NoArgsConstructor
public class ReportEntity {

    @Id
    private String user;

    private String provider;
}
