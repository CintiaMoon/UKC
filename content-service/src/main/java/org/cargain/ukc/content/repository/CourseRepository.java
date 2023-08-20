package org.cargain.ukc.content.repository;

import org.cargain.ukc.content.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
	 //List<CourseModule> findByCourseId(Long courseId);
}
