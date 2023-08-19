package org.cargain.ukc.content.web;

import java.util.List;

import org.cargain.ukc.content.domain.CourseDTO;
import org.cargain.ukc.content.domain.CourseModuleDTO;
import org.cargain.ukc.content.domain.LessonDTO;
import org.cargain.ukc.content.service.CourseModuleService;
import org.cargain.ukc.content.service.CourseService;
import org.cargain.ukc.content.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseModuleService courseModuleService;

    @Autowired
    private LessonService lessonService;

    // Course endpoints
    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{courseId}")
    public CourseDTO getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/courses")
    public CourseDTO createCourse(@RequestBody CourseDTO course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/courses/{courseId}")
    public CourseDTO updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO updatedCourse) {
        return courseService.updateCourse(courseId, updatedCourse);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
    }

    // Module endpoints
//    @GetMapping("/courses/{courseId}/modules")
//    public List<CourseModuleDTO> getModulesByCourseId(@PathVariable Long courseId) {
//        return courseModuleService.getModulesByCourseId(courseId);
//    }

    @PostMapping("/modules")
    public CourseModuleDTO createModule(@RequestBody CourseModuleDTO module) {
        return courseModuleService.createModule(module);
    }

    @PutMapping("/modules/{moduleId}")
    public CourseModuleDTO updateModule(@PathVariable Long moduleId, @RequestBody CourseModuleDTO updatedModule) {
        return courseModuleService.updateModule(moduleId, updatedModule);
    }

    @DeleteMapping("/modules/{moduleId}")
    public void deleteModule(@PathVariable Long moduleId) {
        courseModuleService.deleteModule(moduleId);
    }

    // Lesson endpoints
//    @GetMapping("/modules/{moduleId}/lessons")
//    public List<LessonDTO> getLessonsByModuleId(@PathVariable Long moduleId) {
//        return lessonService.getLessonsByModuleId(moduleId);
//    }

    @PostMapping("/lessons")
    public LessonDTO createLesson(@RequestBody LessonDTO lesson) {
        return lessonService.createLesson(lesson);
    }

    @PutMapping("/lessons/{lessonId}")
    public LessonDTO updateLesson(@PathVariable Long lessonId, @RequestBody LessonDTO updatedLesson) {
        return lessonService.updateLesson(lessonId, updatedLesson);
    }

    @DeleteMapping("/lessons/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
    }
}

