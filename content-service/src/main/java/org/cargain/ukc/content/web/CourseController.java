package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CourseDTO;
import org.cargain.ukc.content.service.CourseService;
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
@RequestMapping("/api/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping
	public List<CourseDTO> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/{courseId}")
	public CourseDTO getCourseById(@PathVariable Long courseId) {
		return courseService.getCourseById(courseId);
	}

	@PostMapping
	public CourseDTO createCourse(@Valid @RequestBody CourseDTO courseDTO) {
		return courseService.createCourse(courseDTO);
	}

	@PutMapping("/{courseId}")
	public CourseDTO updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseDTO courseDTO) {
		return courseService.updateCourse(courseId, courseDTO);
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourse(courseId);
		return ResponseEntity.noContent().build();
	}
}
