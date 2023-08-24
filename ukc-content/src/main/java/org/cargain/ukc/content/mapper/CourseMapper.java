package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseMapper {

    Course toCourse(CreateCourseRequest createCourseRequest);

    CourseResponse toCourseResponse(Course course);

}
