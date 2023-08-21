package org.cargain.ukc.content.service;

import java.util.List;
import java.util.stream.Collectors;
import org.cargain.ukc.commons.events.content.CourseCreatedEvent;
import org.cargain.ukc.commons.utils.kafka.producer.KafkaProducer;
import org.cargain.ukc.commons.utils.mappers.ModelMapperService;
import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.exception.ResourceNotFoundException;
import org.cargain.ukc.content.mapper.CourseMapper;
import org.cargain.ukc.content.model.Course;
import org.cargain.ukc.content.repository.CourseRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper mapper;
	private final ModelMapperService mapperService;
    private final KafkaProducer producer;

	public CourseResponse validateAndGetCourse(Long id) {
		
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
        return mapper.toCourseResponse(course);        
	}

	public List<CourseResponse> getAll() {
		List<Course> courses = courseRepository.findAll();
		return courses.stream().map(course -> mapper.toCourseResponse(course)).collect(Collectors.toList());
	}

	public CourseResponse saveCourse(CreateCourseRequest createCourseRequest) {
		
		Course course = mapper.toCourse(createCourseRequest);
        course = courseRepository.save(course);
       
        sendKafkaCourseCreatedEvent(course);
        CourseResponse courseDtoResponse =  mapper.toCourseResponse(course);
        
        return courseDtoResponse;
	}

	public CourseResponse deleteCourse(Long id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
		
		courseRepository.delete(course);
        return mapper.toCourseResponse(course);
	}
	
	private void sendKafkaCourseCreatedEvent(Course courseCreated)    {
        var event = mapperService.forResponse().map(courseCreated, CourseCreatedEvent.class);
                
        producer.sendMessage(event, "course-created");
    }
}
