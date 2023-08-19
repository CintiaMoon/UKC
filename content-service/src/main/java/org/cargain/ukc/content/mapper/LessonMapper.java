package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.LessonDTO;
import org.cargain.ukc.content.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    LessonDTO lessonToDto(Lesson lesson);
    Lesson dtoToLesson(LessonDTO dto);
}
