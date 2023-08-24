package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.CreateLessonRequest;
import org.cargain.ukc.content.domain.LessonResponse;
import org.cargain.ukc.content.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LessonMapper {

    Lesson toLesson(CreateLessonRequest createLessonRequest);

    LessonResponse toLessonResponse(Lesson lesson);

}