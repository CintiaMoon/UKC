package org.cargain.ukc.content.repository;

import java.util.Optional;

import org.cargain.ukc.content.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    //List<Lesson> findByModuleId(Long moduleId);
    Optional<Lesson> findByIdAndModuleId(Long id, Long moduleId);
}
