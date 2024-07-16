package com.projects.personal.forum_hub.service;

import com.projects.personal.forum_hub.dto.course.DTOCourse;
import com.projects.personal.forum_hub.dto.course.DTOCourseAnswer;
import com.projects.personal.forum_hub.dto.course.DTOUpdateCourse;
import com.projects.personal.forum_hub.models.Course;
import com.projects.personal.forum_hub.repository.CourseRepository;
import com.projects.personal.forum_hub.repository.UserRepository;
import com.projects.personal.forum_hub.understructure.errors.NotExist;
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
        return ResponseEntity.ok(courseRepository.findAll(page).map(DTOCourseAnswer::new));
    }

    public ResponseEntity<DTOCourseAnswer> getById(Long idCourse, Long idAuthor) {
        if (!courseRepository.existsById(idCourse)) {
            return ResponseEntity.notFound().build();
        }
        Optional<Course> course = Optional.of(courseRepository.getReferenceById(idCourse));
        if (course.get().getPublicCourse() == false && idAuthor != course.get().getAuthor_id()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(new DTOCourseAnswer(course.get()));
    }


    public ResponseEntity<DTOCourseAnswer> registerCourse(DTOCourse course, Long idAuthor) throws  NotExist{
        userExist(idAuthor);
        //System.out.println("EXISTEAUTOR");
        var courseAns = new Course(course,idAuthor);
        return ResponseEntity.ok(new DTOCourseAnswer(courseRepository.save(courseAns)));
    }

    public ResponseEntity<DTOCourseAnswer> update(DTOUpdateCourse course, Long idAuthor, Long idCourse) {
        userExist(idAuthor);
        if (!courseRepository.existsById(idCourse)) {
            return ResponseEntity.notFound().build();
        }
        Optional<Course> courseOptional = courseRepository.findById(idCourse);
        if (courseOptional.get().getAuthor_id() != idAuthor) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Course courseUpdate = courseOptional.get();
        courseUpdate.updateData(course);
        return ResponseEntity.ok(new DTOCourseAnswer(courseRepository.save(courseUpdate)));
    }

    public ResponseEntity<DTOCourseAnswer> delete(Long idAuthor, Long idCourse) {
        userExist(idAuthor);
        if ( !courseRepository.existsById(idCourse) ) {
            return ResponseEntity.notFound().build();
        }
        Optional<Course> courseOptional = courseRepository.findById(idCourse);
        if (courseOptional.get().getAuthor_id() != idAuthor) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        courseRepository.deleteById(idCourse);
        return ResponseEntity.noContent().build();
    }

    public void userExist(Long idAuthor) throws NotExist {
        if (!userRepository.existsById(idAuthor)) {
            throw new NotExist("Author not found");
        }
    }
}
