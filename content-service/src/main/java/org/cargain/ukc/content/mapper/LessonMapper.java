package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.CreateLessonRequest;
import org.cargain.ukc.content.domain.LessonResponse;
import org.cargain.ukc.content.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "module", ignore = true)
    Lesson toLesson(CreateLessonRequest createLessonRequest);

    LessonResponse toLessonResponse(Lesson lesson);

}