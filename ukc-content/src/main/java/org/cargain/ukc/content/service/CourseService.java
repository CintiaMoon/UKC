package org.cargain.ukc.content.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.cargain.ukc.commons.events.content.CourseCreatedEvent;
import org.cargain.ukc.commons.utils.kafka.producer.KafkaProducer;
import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.domain.UpdateCourseRequest;
import org.cargain.ukc.content.mapper.CourseMapper;
import org.cargain.ukc.content.model.Course;
import org.cargain.ukc.content.repository.CourseRepository;
import org.cargain.ukc.content.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper mapper;
	private final KafkaProducer producer;
	private final ModelMapper modelMapper;

// ==========================================================================================

	public List<CourseResponse> getAll() {
		List<Course> courses = new ArrayList<Course>();

		courseRepository.findAll().forEach(courses::add);

		return courses.stream().map(course -> mapper.toCourseResponse(course)).collect(Collectors.toList());
	}
	
	public CourseResponse getById(Long id) {
		
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
        return mapper.toCourseResponse(course);        
	}

	public CourseResponse save(CreateCourseRequest createCourseRequest) {

		Course course = mapper.toCourse(createCourseRequest);
		course = courseRepository.save(course);

		sendKafkaCourseEvent(course, Constants.COURSE_CREATED);
		CourseResponse courseDtoResponse = mapper.toCourseResponse(course);

		return courseDtoResponse;

	}

	public CourseResponse update(long courseId, UpdateCourseRequest courseReq) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Mooursedule with Id = " + courseId));

		course.setDescription(courseReq.getDescription());
		course.setPrice(courseReq.getPrice());
		course.setTitle(courseReq.getTitle());
		course = courseRepository.save(course);

		sendKafkaCourseEvent(course, Constants.COURSE_UPDATED);
		CourseResponse courseDtoResponse = mapper.toCourseResponse(course);

		return courseDtoResponse;

	}

	public CourseResponse delete(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));

		courseRepository.delete(course);
		sendKafkaCourseEvent(course, Constants.COURSE_DELETED);
		return mapper.toCourseResponse(course);
	}

	private void sendKafkaCourseEvent(Course courseCreated, String courseEvent) {
		var event = modelMapper.map(courseCreated, CourseCreatedEvent.class);
		producer.sendMessage(event, courseEvent);
	}
// =======================================================================================================
// =======================================================================================================

}
