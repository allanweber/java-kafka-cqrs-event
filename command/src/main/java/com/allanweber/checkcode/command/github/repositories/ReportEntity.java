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
    private String user;

    private String provider;

    private String avatar;

    private String repos;

    public ReportEntity(String user, String provider, String avatar, String repos) {
        this.user = user;
        this.provider = provider;
        this.avatar = avatar;
        this.repos = repos;
    }
}
