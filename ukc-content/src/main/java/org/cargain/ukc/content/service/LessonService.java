package org.cargain.ukc.content.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.cargain.ukc.content.domain.CreateLessonRequest;
import org.cargain.ukc.content.domain.LessonResponse;
import org.cargain.ukc.content.domain.UpdateLessonRequest;
import org.cargain.ukc.content.mapper.LessonMapper;
import org.cargain.ukc.content.model.Lesson;
import org.cargain.ukc.content.model.Module;
import org.cargain.ukc.content.repository.LessonRepository;
import org.cargain.ukc.content.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonService {

	private final LessonRepository lessonRepository;
	private final ModuleRepository moduleRepository;
	private final LessonMapper mapper;

	public List<LessonResponse> getAll() {
		List<Lesson> lessons = new ArrayList<Lesson>();

		lessonRepository.findAll().forEach(lessons::add);

		return lessons.stream().map(course -> mapper.toLessonResponse(course)).collect(Collectors.toList());

	}

	public LessonResponse getById(Long lessonId) {

		Lesson lesson = lessonRepository.findById(lessonId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + lessonId));

		return mapper.toLessonResponse(lesson);
	}

	public LessonResponse save(long moduleId, CreateLessonRequest lessonReq) {

		Lesson lesson = mapper.toLesson(lessonReq);
		Module module = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + moduleId));
		module.getLessons().add(lesson);

		return mapper.toLessonResponse(lessonRepository.save(lesson));

	}

	public LessonResponse update(long lessonId, UpdateLessonRequest lessonReq) {
		Lesson lesson = lessonRepository.findById(lessonId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + lessonId));

		lesson.setTitle(lessonReq.getTitle());
		lesson.setContent(lessonReq.getContent());
		
		lessonRepository.save(lesson);
		
		return mapper.toLessonResponse(lesson);
	}

	public LessonResponse delete(long lessonId) {
		
		Lesson lesson = lessonRepository.findById(lessonId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + lessonId));
		lessonRepository.delete(lesson);		
		return mapper.toLessonResponse(lesson);
	}
}
