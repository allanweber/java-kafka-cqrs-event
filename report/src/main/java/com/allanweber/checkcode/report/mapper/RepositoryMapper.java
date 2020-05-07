package com.allanweber.checkcode.report.mapper;

import com.allanweber.checkcode.common.dto.RepositoryProperties;
import com.allanweber.checkcode.github.dto.Repository;
import com.allanweber.checkcode.report.repositories.GitRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RepositoryMapper {

    GitRepository toGitRepository(Repository dto);

    List<GitRepository> toGitRepository(List<Repository> dto);

    @Mapping(target = "name", source = "gitRepository.name")
    @Mapping(target = "id", source = "gitRepository.id")
    RepositoryProperties toRepositoryProperties(String reportId, GitRepository gitRepository);

    default List<RepositoryProperties> toRepositoryProperties(String reportId, List<GitRepository> gitRepository) {
        return gitRepository.stream()
                .map(repo -> this.toRepositoryProperties(reportId, repo))
                .collect(Collectors.toList());
    }
}
