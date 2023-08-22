package org.cargain.ukc.content.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.cargain.ukc.content.domain.CreateModuleRequest;
import org.cargain.ukc.content.domain.ModulesResponse;
import org.cargain.ukc.content.domain.UpdateModuleRequest;
import org.cargain.ukc.content.mapper.ModuleMapper;
import org.cargain.ukc.content.model.Course;
import org.cargain.ukc.content.model.Module;
import org.cargain.ukc.content.repository.CourseRepository;
import org.cargain.ukc.content.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModuleService {

	private final ModuleRepository moduleRepository;
	private final CourseRepository courseRepository;
	private final ModuleMapper mapper;

// ==============================================================================================================
	public List<ModulesResponse> getAll() {

		List<Module> modules = new ArrayList<Module>();

		moduleRepository.findAll().forEach(modules::add);

		return modules.stream().map(course -> mapper.toModuleResponse(course)).collect(Collectors.toList());
	}

	public ModulesResponse save(long courseId, CreateModuleRequest moduleReq) {

		Module module = mapper.toModule(moduleReq);

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Mooursedule with Id = " + courseId));
		course.getModules().add(module);

		moduleRepository.save(module);

		return mapper.toModuleResponse(module);

	}

	public ModulesResponse getById(Long moduleId) {
		Module module = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + moduleId));

		return mapper.toModuleResponse(module);

	}

	public ModulesResponse update(long moduleId, UpdateModuleRequest moduleReq) {
		Module module = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + moduleId));
		
		module.setDescription(moduleReq.getDescription());
		module.setTitle(moduleReq.getTitle());
		
		moduleRepository.save(module);

		return mapper.toModuleResponse(module);
	}

	public ModulesResponse delete(Long moduleId) {

		Module module = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Module with Id = " + moduleId));
		
		moduleRepository.delete(module);
		return mapper.toModuleResponse(module);
	}

// ==============================================================================================================

}
