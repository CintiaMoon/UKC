package org.cargain.ukc.content.repository;

import java.util.List;

import org.cargain.ukc.content.model.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {
	List<CourseModule> findByCourseId(Long courseId);
}
