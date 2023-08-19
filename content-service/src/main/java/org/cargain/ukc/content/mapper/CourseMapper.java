package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.CourseDTO;
import org.cargain.ukc.content.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO courseToDto(Course course);
    Course dtoToCourse(CourseDTO dto);
}
