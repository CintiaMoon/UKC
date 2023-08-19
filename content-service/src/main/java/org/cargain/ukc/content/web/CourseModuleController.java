package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CourseModuleDTO;
import org.cargain.ukc.content.service.CourseModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course-modules")
public class CourseModuleController {

	@Autowired
	private CourseModuleService courseModuleService;

	@GetMapping
	public List<CourseModuleDTO> getAllCourses() {
		return courseModuleService.getAllCourses();
	}

//	@GetMapping("/{courseId}")
//	public List<CourseModuleDTO> getCourseById(@PathVariable Long courseId) {
//		return courseModuleService.getModulesByCourseId(courseId);
//	}

	@PostMapping
	public CourseModuleDTO createCourse(@Valid @RequestBody CourseModuleDTO CourseModuleDTO) {
		return courseModuleService.createModule(CourseModuleDTO);
	}

	@PutMapping("/{courseId}")
	public CourseModuleDTO updateCourse(@PathVariable Long courseId,
			@Valid @RequestBody CourseModuleDTO courseModuleDTO) {
		return courseModuleService.updateModule(courseId, courseModuleDTO);
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
		courseModuleService.deleteModule(courseId);
		return ResponseEntity.noContent().build();
	}
}
