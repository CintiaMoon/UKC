package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CreateLessonRequest;
import org.cargain.ukc.content.domain.LessonResponse;
import org.cargain.ukc.content.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

	private final LessonService lessonService;

	@GetMapping
	public List<LessonResponse> getAllLessons() {
		return lessonService.getAll();
	}

	@GetMapping("/{moduleId}/{lessonId}")
	public LessonResponse getModule(@PathVariable Long moduleId, @PathVariable Long lessonId) {
		return lessonService.validateAndGetLesson(moduleId, lessonId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{moduleId}")
	public LessonResponse createModule(@PathVariable Long moduleId,
			@Valid @RequestBody CreateLessonRequest createLessonRequest) {
		return lessonService.saveLesson(moduleId, createLessonRequest);
	}
}
