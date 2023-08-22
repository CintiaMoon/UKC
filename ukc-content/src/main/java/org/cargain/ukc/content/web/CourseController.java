package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.domain.UpdateCourseRequest;
import org.cargain.ukc.content.repository.CourseRepository;
import org.cargain.ukc.content.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/courses") // http://localhost:8585/course
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseService courseService;

//*************************************************** : CRUD Operation : *****************************************************************	  
//=======================================================================================================================================
	@GetMapping
	public ResponseEntity<List<CourseResponse>> getAll() {
		List<CourseResponse> course = courseService.getAll();

		if (course.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<CourseResponse> getCourse(@PathVariable Long courseId) {

		return new ResponseEntity<>(courseService.getById(courseId), HttpStatus.OK);
	}

//=======================================================================================================================================

	@PostMapping
	public ResponseEntity<CourseResponse> save(@RequestBody CreateCourseRequest course) {
	
		return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);
	}

	@PutMapping("/{courseId}")
	public ResponseEntity<CourseResponse> update(@PathVariable("courseId") long courseId,
			@RequestBody UpdateCourseRequest course) {

		return new ResponseEntity<>(courseService.update(courseId, course), HttpStatus.OK);
	}

	@DeleteMapping("/{courseId}")
	public CourseResponse deleteCourse(@PathVariable Long courseId) {
		return courseService.delete(courseId);
	}

// =================================================================================================================	  
// =================================================================================================================	  

}
