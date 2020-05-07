package com.allanweber.checkcode.query.github.repository.repositories;

import com.allanweber.checkcode.query.github.repository.dto.Languages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "repository-languages")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryLanguagesEntity {

    @Id
    private Long id;

    private String reportId;

    private String name;

    private List<Languages> languages;
}
