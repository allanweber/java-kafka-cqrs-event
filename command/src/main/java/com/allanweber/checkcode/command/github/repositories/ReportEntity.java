package com.allanweber.checkcode.command.github.repositories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "report")
@Getter
@NoArgsConstructor
public class ReportEntity {

    @Id
    private String id;

    private String user;

    private String provider;

    public ReportEntity(String user, String provider) {
        this.user = user;
        this.provider = provider;
    }
}
