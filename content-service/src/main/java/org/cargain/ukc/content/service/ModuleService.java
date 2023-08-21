package org.cargain.ukc.content.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cargain.ukc.content.domain.CreateModuleRequest;
import org.cargain.ukc.content.domain.ModulesResponse;
import org.cargain.ukc.content.exception.ResourceNotFoundException;
import org.cargain.ukc.content.mapper.ModuleMapper;
import org.cargain.ukc.content.model.Course;
import org.cargain.ukc.content.model.Module;
import org.cargain.ukc.content.repository.CourseRepository;
import org.cargain.ukc.content.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModuleService {

	private final ModuleRepository moduleRepository;
	private final ModuleMapper mapper;
	private final CourseRepository courseRepository;

	public ModulesResponse validateAndGetModule(Long moduleId, Long courseId) {

		Module course = moduleRepository.findByIdAndCourseId(moduleId, courseId).orElseThrow(
				() -> new ResourceNotFoundException("Module : " + moduleId + " not found for course " + courseId));
		return mapper.toModuleResponse(course);
	}

	public List<ModulesResponse> getAll() {
		List<Module> modules = moduleRepository.findAll();
		return modules.stream().map(course -> mapper.toModuleResponse(course)).collect(Collectors.toList());
	}
	
	public ModulesResponse saveModule(Long courseId, CreateModuleRequest createModuleRequest) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));
		Module module = mapper.toModule(createModuleRequest);
		module.addCourse(course);

		moduleRepository.save(module);
		ModulesResponse moduleResponse = mapper.toModuleResponse(module);
		return moduleResponse;

	}

	public ModulesResponse deleteModule(Long courseId, Long moduleId) {
		//moduleRepository.delete(module);
		
		Module module = moduleRepository.findByIdAndCourseId(moduleId, courseId).orElseThrow(
				() -> new ResourceNotFoundException("Module : " + moduleId + " not found for course " + courseId));
		moduleRepository.delete(module);
		ModulesResponse moduleResponse = mapper.toModuleResponse(module);
		return moduleResponse;
        
	}
}
