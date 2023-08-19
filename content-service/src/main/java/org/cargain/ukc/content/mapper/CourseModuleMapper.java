package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.CourseModuleDTO;
import org.cargain.ukc.content.model.CourseModule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseModuleMapper {
    CourseModuleMapper INSTANCE = Mappers.getMapper(CourseModuleMapper.class);

    CourseModuleDTO courseModuleToDto(CourseModule courseModule);
    CourseModule dtoToCourseModule(CourseModuleDTO dto);
}
