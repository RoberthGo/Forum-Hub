package com.projects.personal.forum_hub.controller;

import com.projects.personal.forum_hub.dto.course.DTOCourse;
import com.projects.personal.forum_hub.dto.course.DTOCourseAnswer;
import com.projects.personal.forum_hub.dto.course.DTOUpdateCourse;
import com.projects.personal.forum_hub.service.ServiceCourse;
import com.projects.personal.forum_hub.understructure.errors.NotExist;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/course")
@SecurityRequirement(name = "bearer-key")
public class ControllerCourse {

    @Autowired
    ServiceCourse serviceCourse;

    @GetMapping
    public ResponseEntity<Page<DTOCourseAnswer>> getAllCoursePublic(@PageableDefault(size = 5, sort = {"name"}) Pageable page) {
        return serviceCourse.getAll(page);
    }

    @GetMapping("/{idAuthor}/{idCourse}")
    public ResponseEntity<DTOCourseAnswer> getCourseById(@PathVariable Long idAuthor, @PathVariable Long idCourse) {
        return serviceCourse.getById(idCourse, idAuthor);
    }

    @PostMapping("/{idAuthor}")
    public ResponseEntity<DTOCourseAnswer> registerCourse(@RequestBody @Valid DTOCourse course, @PathVariable Long idAuthor) throws NotExist {
        return serviceCourse.registerCourse(course, idAuthor);
    }

    @PutMapping("/{idAuthor}/{idCourse}")
    public ResponseEntity<DTOCourseAnswer> registerCourse(@RequestBody DTOUpdateCourse course, @PathVariable Long idAuthor, @PathVariable Long idCourse) {
        return serviceCourse.update(course, idAuthor, idCourse);
    }

    @DeleteMapping("/{idAuthor}/{idCourse}")
    public ResponseEntity<DTOCourseAnswer> deleteCourse(@PathVariable Long idAuthor, @PathVariable Long idCourse) {
        return serviceCourse.delete(idAuthor,idCourse);
    }

}
