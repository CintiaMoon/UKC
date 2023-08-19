package org.cargain.ukc.content.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cargain.ukc.content.domain.LessonDTO;
import org.cargain.ukc.content.mapper.LessonMapper;
import org.cargain.ukc.content.model.Lesson;
import org.cargain.ukc.content.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonDTO> getAllLessons() {        
        List<Lesson> curses = lessonRepository.findAll();
        return curses.stream()
                .map(LessonMapper.INSTANCE::lessonToDto)
                .collect(Collectors.toList());
    }
    
//    public List<LessonDTO> getLessonsByModuleId(Long moduleId) {
//        List<Lesson> lessons = lessonRepository.findByModuleId(moduleId);
//        return lessons.stream()
//            .map(LessonMapper.INSTANCE::lessonToDto)
//            .collect(Collectors.toList());
//    }

    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Lesson lesson = LessonMapper.INSTANCE.dtoToLesson(lessonDTO);
        lesson = lessonRepository.save(lesson);
        return LessonMapper.INSTANCE.lessonToDto(lesson);
    }

    public LessonDTO updateLesson(Long lessonId, LessonDTO updatedLesson) {
        Lesson existingLesson = lessonRepository.findById(lessonId).orElse(null);
        if (existingLesson != null) {
            existingLesson.setTitle(updatedLesson.getTitle());
            existingLesson.setContent(updatedLesson.getContent());
            // Update other properties as needed
            return LessonMapper.INSTANCE.lessonToDto(lessonRepository.save(existingLesson));
        }
        return null;
    }

    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
