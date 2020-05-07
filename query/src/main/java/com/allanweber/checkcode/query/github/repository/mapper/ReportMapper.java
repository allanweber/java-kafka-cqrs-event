package com.allanweber.checkcode.query.github.repository.mapper;

import com.allanweber.checkcode.query.github.repository.dto.Report;
import com.allanweber.checkcode.query.github.repository.dto.RepositoryLanguages;
import com.allanweber.checkcode.query.github.repository.repositories.ReportEntity;
import com.allanweber.checkcode.query.github.repository.repositories.RepositoryLanguagesEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReportMapper {

    Report fromEntity(ReportEntity entity);

    RepositoryLanguages fromEntity(RepositoryLanguagesEntity entity);
}
