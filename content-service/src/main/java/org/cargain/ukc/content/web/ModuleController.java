package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CreateModuleRequest;
import org.cargain.ukc.content.domain.ModulesResponse;
import org.cargain.ukc.content.service.ModuleService;
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
@RequestMapping("/api/modules")
public class ModuleController {
	
    private final ModuleService moduleService;
    
    @GetMapping
    public List<ModulesResponse> getAllModules() {
        return moduleService.getAll();
    }

    
    @GetMapping("/{courseId}/{moduleId}")
    public ModulesResponse getModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
        return moduleService.validateAndGetModule(moduleId, courseId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{courseId}")
    public ModulesResponse createModule(@PathVariable Long courseId,
                                   @Valid @RequestBody CreateModuleRequest createModuleRequest) {
        return moduleService.saveModule(courseId, createModuleRequest);
    }

//    @PutMapping("/{courseId}/modules/{moduleId}")
//    public ModuleResponse updateModule(@PathVariable Long courseId,
//                                   @PathVariable Long moduleId,
//                                   @Valid @RequestBody UpdateModuleRequest updateModuleRequest) {
//        Module module = moduleService.validateAndGetModule(moduleId, courseId);
//        moduleMapper.updateModuleFromRequest(updateModuleRequest, module);
//        module = moduleService.saveModule(module);
//        return moduleMapper.toModuleResponse(module);
//    }

    @DeleteMapping("/{courseId}/{moduleId}")
    public ModulesResponse deleteModule(@PathVariable Long courseId, @PathVariable Long moduleId) {
        
        return moduleService.deleteModule(moduleId, courseId);
    }

}
