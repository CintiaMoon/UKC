package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.LessonDTO;
import org.cargain.ukc.content.service.LessonService;
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
@RequestMapping("/api/lessons")
public class LessonController {

	@Autowired
	private LessonService lessonService;

	@GetMapping
	public List<LessonDTO> getAllCourses() {
		return lessonService.getAllLessons();
	}

//	@GetMapping("/{lessonId}")
//	public List<LessonDTO> getLessonById(@PathVariable Long lessonId) {
//		return lessonService.getLessonsByModuleId(lessonId);
//	}

	@PostMapping
	public LessonDTO createLesson(@Valid @RequestBody LessonDTO LessonDTO) {
		return lessonService.createLesson(LessonDTO);
	}

	@PutMapping("/{lessonId}")
	public LessonDTO updateCourse(@PathVariable Long lessonId,
			@Valid @RequestBody LessonDTO lessonDTO) {
		return lessonService.updateLesson(lessonId, lessonDTO);
	}

	@DeleteMapping("/{lessonId}")
	public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
		lessonService.deleteLesson(lessonId);
		return ResponseEntity.noContent().build();
	}
}
