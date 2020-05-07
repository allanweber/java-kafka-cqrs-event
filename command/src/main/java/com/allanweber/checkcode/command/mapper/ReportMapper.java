package com.allanweber.checkcode.command.mapper;

import com.allanweber.checkcode.command.github.repositories.ReportEntity;
import com.allanweber.checkcode.common.dto.ReportProperties;
import org.mapstruct.Mapper;

@Mapper
public interface ReportMapper {

    ReportProperties fromEntity(ReportEntity entity);
}
