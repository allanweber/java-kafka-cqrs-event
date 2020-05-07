package com.allanweber.checkcode.report.repositories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "report")
@Getter
@NoArgsConstructor
public class ReportEntity {

    @Id
    private String id;

    private String user;

    private String provider;

    private List<GitRepository> repositories;

    public ReportEntity(String user, String provider) {
        this.user = user;
        this.provider = provider;
    }

    public ReportEntity addRepositories(List<GitRepository> repositories) {
        if (repositories == null) {
            throw new IllegalArgumentException("List<GitRepository>");
        }
        repositories.forEach(this::addRepository);
        return this;
    }

    private void addRepository(GitRepository repository){
        if (repository == null) {
            throw new IllegalArgumentException(GitRepository.class.getSimpleName());
        }

        if(this.repositories == null){
            this.repositories = new ArrayList<>();
        }

        this.repositories.add(repository);
    }
}
