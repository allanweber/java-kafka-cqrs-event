package com.allanweber.checkcode.command.mapper;

import com.allanweber.checkcode.command.github.repositories.ReportEntity;
import com.allanweber.checkcode.common.dto.ReportProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SpringExtension.class})
class ReportMapperTest {

    private final ReportMapper mapper = Mappers.getMapper(ReportMapper.class);

    @Test
    public void map() {
        ReportEntity entity = new ReportEntity("user", "git");
        ReportProperties properties = mapper.fromEntity(entity);

        assertEquals("user", properties.getUser());
        assertEquals("git", properties.getProvider());
    }

}