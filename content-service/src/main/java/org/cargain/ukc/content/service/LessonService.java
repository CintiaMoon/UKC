package org.cargain.ukc.content.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cargain.ukc.content.domain.CreateLessonRequest;
import org.cargain.ukc.content.domain.LessonResponse;
import org.cargain.ukc.content.exception.ResourceNotFoundException;
import org.cargain.ukc.content.mapper.LessonMapper;
import org.cargain.ukc.content.model.Lesson;
import org.cargain.ukc.content.model.Module;
import org.cargain.ukc.content.repository.LessonRepository;
import org.cargain.ukc.content.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService {

	private final LessonRepository lessonRepository;

	private final LessonMapper mapper;
	private final ModuleRepository moduleRepository;

	public LessonResponse validateAndGetLesson(Long moduleId, Long lessonId) {
		Lesson lesson = lessonRepository.findByIdAndModuleId(lessonId, moduleId).orElseThrow(
				() -> new ResourceNotFoundException("Module : " + moduleId + " not found for lesson " + lessonId));
		return mapper.toLessonResponse(lesson);
	}

	public List<LessonResponse> getAll() {
		List<Lesson> lessons = lessonRepository.findAll();
		return lessons.stream().map(lesson -> mapper.toLessonResponse(lesson)).collect(Collectors.toList());
	}


	public LessonResponse saveLesson(Long moduleId, CreateLessonRequest createLessonRequest) {

		Module module = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Module not found: " + moduleId));
		
		Lesson lesson = mapper.toLesson(createLessonRequest);
		lesson.addModule(module);

		lessonRepository.save(lesson);
		LessonResponse moduleResponse = mapper.toLessonResponse(lesson);
		return moduleResponse;

	}

	public LessonResponse deleteLesson(Long lessonId, Long moduleId) {
		Lesson lesson = lessonRepository.findByIdAndModuleId(lessonId, moduleId).orElseThrow(
				() -> new ResourceNotFoundException("Module : " + moduleId + " not found for course " + lessonId));
		lessonRepository.delete(lesson);
		LessonResponse lessonResponse = mapper.toLessonResponse(lesson);
		return lessonResponse;
	}

}
