package com.allanweber.checkcode.query.github.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepositoryLanguages {

    private String name;

    private List<Languages> languages;
}
