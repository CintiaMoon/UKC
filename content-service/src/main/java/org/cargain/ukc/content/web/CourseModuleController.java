//package org.cargain.ukc.content.web;
//
//import java.util.List;
//
//import org.cargain.ukc.content.domain.CourseResponse;
//import org.cargain.ukc.content.domain.CreateCourseRequest;
//import org.cargain.ukc.content.domain.CreateModuleRequest;
//import org.cargain.ukc.content.domain.ModulesResponse;
//import org.cargain.ukc.content.mapper.CourseMapper;
//import org.cargain.ukc.content.mapper.ModuleMapper;
//import org.cargain.ukc.content.model.Course;
//import org.cargain.ukc.content.model.Module;
//import org.cargain.ukc.content.service.CourseService;
//import org.cargain.ukc.content.service.ModuleService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1/courses")
//public class CourseModuleController {
//
//    private final CourseService courseService;
//    private final ModuleService moduleService;
//    private final CourseMapper courseMapper;
//    private final ModuleMapper moduleMapper;
//
//    //-----------
//    // Course
//    
//    
//
//    //-----
//    // Module
//
//    @GetMapping("/{courseId}/modules/{moduleId}")
//    public ModulesResponse getModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
//        Module module = moduleService.validateAndGetModule(moduleId, courseId);
//        return moduleMapper.toModuleResponse(module);
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/{courseId}/modules")
//    public ModulesResponse createModule(@PathVariable Long courseId,
//                                   @Valid @RequestBody CreateModuleRequest createModuleRequest) {
//        Course course = courseService.validateAndGetCourse(courseId);
//        Module module = moduleMapper.toModule(createModuleRequest);
//        module.addCourse(course);
//        module = moduleService.saveModule(module);
//        return moduleMapper.toModuleResponse(module);
//    }
//
////    @PutMapping("/{courseId}/modules/{moduleId}")
////    public ModuleResponse updateModule(@PathVariable Long courseId,
////                                   @PathVariable Long moduleId,
////                                   @Valid @RequestBody UpdateModuleRequest updateModuleRequest) {
////        Module module = moduleService.validateAndGetModule(moduleId, courseId);
////        moduleMapper.updateModuleFromRequest(updateModuleRequest, module);
////        module = moduleService.saveModule(module);
////        return moduleMapper.toModuleResponse(module);
////    }
//
//    @DeleteMapping("/{courseId}/modules/{moduleId}")
//    public ModulesResponse deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
//        Module module = moduleService.validateAndGetModule(moduleId, courseId);
//        module.removeCourse();
//        moduleService.deleteModule(module);
//        return moduleMapper.toModuleResponse(module);
//    }
//}
