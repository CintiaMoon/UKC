package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CreateModuleRequest;
import org.cargain.ukc.content.domain.ModulesResponse;
import org.cargain.ukc.content.domain.UpdateModuleRequest;
import org.cargain.ukc.content.repository.CourseRepository;
import org.cargain.ukc.content.service.ModuleService;
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
@RequestMapping("/modules")
public class ModuleController {

	@Autowired
	ModuleService moduleService;

	@Autowired
	CourseRepository passRepo;

//====================================================== : CRUD : =============================================================

	@GetMapping
	public ResponseEntity<List<ModulesResponse>> getAll() {

		List<ModulesResponse> modules = moduleService.getAll();

		if (modules.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(modules, HttpStatus.OK);
	}

	@GetMapping("/{moduleId}")
	public ResponseEntity<ModulesResponse> getById(@PathVariable(value = "moduleId") long moduleId) {

		ModulesResponse module = moduleService.getById(moduleId);
		return new ResponseEntity<ModulesResponse>(module, HttpStatus.OK);
	}

//======================================================================================================================================

	@PostMapping("/{courseId}")
	public ResponseEntity<ModulesResponse> createModule(@PathVariable(value = "courseId") long courseId,
			@RequestBody CreateModuleRequest moduleReq) {

		ModulesResponse module = moduleService.save(courseId, moduleReq);

		return new ResponseEntity<>(module, HttpStatus.CREATED);
	}

	@PutMapping("/{moduleId}")
	public ResponseEntity<ModulesResponse> update(@PathVariable("moduleId") long moduleId,
			@RequestBody UpdateModuleRequest moduleReq) {

		return new ResponseEntity<>(moduleService.update(moduleId, moduleReq), HttpStatus.OK);
	}

	@DeleteMapping("/{moduleId}")
	public ResponseEntity<ModulesResponse> delete(@PathVariable Long moduleId) {
		return new ResponseEntity<>(moduleService.delete(moduleId), HttpStatus.OK);
	}

//======================================================================================================================================
//======================================================================================================================================

}
