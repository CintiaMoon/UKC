package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/courses")
public class CourseController {
	
	private final CourseService courseService;
	
	@GetMapping
    public List<CourseResponse> getAllCourse() {
        return courseService.getAll();
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourse(@PathVariable Long courseId) {
        return courseService.validateAndGetCourse(courseId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseResponse createCourse(@Valid @RequestBody CreateCourseRequest createCourseRequest) {

    	return courseService.saveCourse(createCourseRequest);
    }

//    @PutMapping("/{courseId}")
//    public CourseResponse updateCourse(@PathVariable Long courseId,
//                                               @Valid @RequestBody UpdateCourseRequest updateCourseRequest) {
//        Course course = courseService.validateAndGetCourse(courseId);
//        courseMapper.updateCourseFromRequest(updateCourseRequest, course);
//        courseService.saveCourse(course);
//        return courseMapper.toCourseResponse(course);
//    }

    @DeleteMapping("/{courseId}")
    public CourseResponse deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourse(courseId);
    }

}
