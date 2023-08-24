package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CreateLessonRequest;
import org.cargain.ukc.content.domain.LessonResponse;
import org.cargain.ukc.content.domain.UpdateLessonRequest;
import org.cargain.ukc.content.service.LessonService;
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
@RequestMapping("/lessons") // http://localhost:8585/head_count
public class LessonController {

	@Autowired
	LessonService lessonService;

//*************************************************** : CRUD Operation : *****************************************************************	  
//=======================================================================================================================================

	@GetMapping
	public ResponseEntity<List<LessonResponse>> getAll() {

		List<LessonResponse> lessons = lessonService.getAll();

		if (lessons.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lessons, HttpStatus.OK);
	}

	@GetMapping("/{lessonId}")
	public ResponseEntity<LessonResponse> getById(@PathVariable(value = "lessonId") long lessonId) {

		return new ResponseEntity<LessonResponse>(lessonService.getById(lessonId), HttpStatus.OK);
	}

//======================================================================================================================================

	@PostMapping("/{moduleId}")
	public ResponseEntity<LessonResponse> save(@PathVariable(value = "moduleId") long moduleId,
			@RequestBody CreateLessonRequest lessonReq) {

		return new ResponseEntity<>(lessonService.save(moduleId, lessonReq), HttpStatus.CREATED);
	}

	@PutMapping("/{lessonId}")
	public ResponseEntity<LessonResponse> update(@PathVariable(value = "lessonId") long lessonId,
			@RequestBody UpdateLessonRequest lessonReq) {

		return new ResponseEntity<LessonResponse>(lessonService.update(lessonId, lessonReq), HttpStatus.CREATED);
	}

	@DeleteMapping("/{lessonId}")
	public ResponseEntity<LessonResponse> delete(@PathVariable(value = "lessonId") long lessonId) {

		return new ResponseEntity<>(lessonService.delete(lessonId), HttpStatus.CREATED);
	}

//======================================================================================================================================	  		  
//======================================================================================================================================	  

}
