package com.projects.personal.forum_hub.repository;

import com.projects.personal.forum_hub.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
