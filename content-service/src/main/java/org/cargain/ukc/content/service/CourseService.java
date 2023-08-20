package org.cargain.ukc.content.service;

import java.util.List;
import java.util.stream.Collectors;
import org.cargain.ukc.commons.events.content.CourseCreatedEvent;
import org.cargain.ukc.commons.utils.kafka.producer.KafkaProducer;
import org.cargain.ukc.commons.utils.mappers.ModelMapperService;

import org.cargain.ukc.content.domain.CourseDTO;
import org.cargain.ukc.content.exception.ResourceNotFoundException;
import org.cargain.ukc.content.mapper.CourseMapper;
import org.cargain.ukc.content.model.Course;
import org.cargain.ukc.content.repository.CourseRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;

    public List<CourseDTO> getAllCourses() {        
        List<Course> curses = courseRepository.findAll();
        return curses.stream()
                .map(CourseMapper.INSTANCE::courseToDto)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long courseId) { 
    	 Course course = courseRepository.findById(courseId)
    	            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    	        return CourseMapper.INSTANCE.courseToDto(course);    
    }

    public CourseDTO createCourse(CourseDTO courseDto) {
    	Course course = CourseMapper.INSTANCE.dtoToCourse(courseDto);
    	
    	var courseSaved = courseRepository.save(course);
        CourseDTO courseDtoResponse =  CourseMapper.INSTANCE.courseToDto(courseSaved);
        sendKafkaCourseCreatedEvent(courseSaved);
        
        return courseDtoResponse;
    }
    
    private void sendKafkaCourseCreatedEvent(Course courseCreated)    {
        var event = mapper.forResponse().map(courseCreated, CourseCreatedEvent.class);
                
        producer.sendMessage(event, "course-created");
    }

    public CourseDTO updateCourse(Long courseId, CourseDTO updatedCourse) {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse != null) {
            existingCourse.setTitle(updatedCourse.getTitle());
            existingCourse.setDescription(updatedCourse.getDescription());
            // Update other properties as needed
            return CourseMapper.INSTANCE.courseToDto(courseRepository.save(existingCourse));
        }
        return null;
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
