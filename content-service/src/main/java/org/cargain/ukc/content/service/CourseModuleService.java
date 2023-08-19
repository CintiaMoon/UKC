package org.cargain.ukc.content.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cargain.ukc.content.domain.CourseModuleDTO;
import org.cargain.ukc.content.mapper.CourseModuleMapper;
import org.cargain.ukc.content.model.CourseModule;
import org.cargain.ukc.content.repository.CourseModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseModuleService {
    @Autowired
    private CourseModuleRepository courseModuleRepository;

    
    public List<CourseModuleDTO> getAllCourses() {        
        List<CourseModule> curses = courseModuleRepository.findAll();
        return curses.stream()
                .map(CourseModuleMapper.INSTANCE::courseModuleToDto)
                .collect(Collectors.toList());
    }

    
//    public List<CourseModuleDTO> getModulesByCourseId(Long courseId) {
//        List<CourseModule> modules = courseModuleRepository.findByCourseId(courseId);
//        return modules.stream()
//            .map(CourseModuleMapper.INSTANCE::courseModuleToDto)
//            .collect(Collectors.toList());
//    }

    public CourseModuleDTO createModule(CourseModuleDTO moduleDto) {
    	CourseModule module = CourseModuleMapper.INSTANCE.dtoToCourseModule(moduleDto);
        module = courseModuleRepository.save(module);
        return CourseModuleMapper.INSTANCE.courseModuleToDto(module);
    }

    public CourseModuleDTO updateModule(Long moduleId, CourseModuleDTO updatedModule) {
        CourseModule existingModule = courseModuleRepository.findById(moduleId).orElse(null);
        if (existingModule != null) {
            existingModule.setTitle(updatedModule.getTitle());
            existingModule.setDescription(updatedModule.getDescription());
            // Update other properties as needed
            return CourseModuleMapper.INSTANCE.courseModuleToDto(courseModuleRepository.save(existingModule));
        }
        return null;
    }

    public void deleteModule(Long moduleId) {
        courseModuleRepository.deleteById(moduleId);
    }
}
