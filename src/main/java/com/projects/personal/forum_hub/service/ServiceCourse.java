package com.projects.personal.forum_hub.service;

import com.projects.personal.forum_hub.dto.course.DTOCourse;
import com.projects.personal.forum_hub.dto.course.DTOCourseAnswer;
import com.projects.personal.forum_hub.dto.course.DTOUpdateCourse;
import com.projects.personal.forum_hub.models.Course;
import com.projects.personal.forum_hub.repository.CourseRepository;
import com.projects.personal.forum_hub.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceCourse {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Page<DTOCourseAnswer>> getAll(Pageable page) {
        Page<Course> coursePage = courseRepository.findAll(page);
        List<DTOCourseAnswer> dtoCourseAnswers = coursePage.stream()
                .filter(Course::getPublicCourse)
                .map(DTOCourseAnswer::new)
                .collect(Collectors.toList());
        Page<DTOCourseAnswer> dtoPage = new PageImpl<>(dtoCourseAnswers, page, coursePage.getTotalElements());
        return ResponseEntity.ok(dtoPage);
    }

    public ResponseEntity<DTOCourseAnswer> getById(Long idCourse, Long idAuthor) {
        Optional<Course> course = Optional.of(courseRepository.getReferenceById(idCourse));
        if (!course.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (course.get().getPublicCourse() == false && idAuthor != course.get().getAuthor_id()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(new DTOCourseAnswer(course.get()));
    }


    public ResponseEntity<DTOCourseAnswer> registerCourse(@Valid DTOCourseAnswer course, Long idAuthor) {
        userExist(idAuthor);
        var courseAns = new Course(course);
        return ResponseEntity.ok(new DTOCourseAnswer(courseRepository.save(courseAns)));
    }

    public ResponseEntity<DTOCourseAnswer> update(DTOUpdateCourse course, Long idAuthor, Long idCourse) {
        userExist(idAuthor);
        Optional<Course> courseOptional = courseRepository.findById(idCourse);
        if (!courseOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (courseOptional.get().getAuthor_id() != idAuthor) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Course courseUpdate = courseOptional.get();
        courseUpdate.updateData(course);
        return ResponseEntity.ok(new DTOCourseAnswer(courseRepository.save(courseUpdate)));
    }

    public ResponseEntity<DTOCourseAnswer> delete(Long idAuthor, Long idCourse) {
        userExist(idAuthor);
        Optional<Course> courseOptional = courseRepository.findById(idCourse);
        if (!courseOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (courseOptional.get().getAuthor_id() != idAuthor) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        courseRepository.deleteById(idCourse);
        return ResponseEntity.noContent().build();
    }

    public void userExist(Long idAuthor) {
        if (!userRepository.existsById(idAuthor)) {
            throw new IllegalArgumentException("Author not found");
        }
    }
}
