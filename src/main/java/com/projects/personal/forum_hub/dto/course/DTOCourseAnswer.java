package com.projects.personal.forum_hub.dto.course;

import com.projects.personal.forum_hub.models.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOCourseAnswer(
        @NotBlank
        String name,
        @NotBlank
        String category,
        @NotNull
        Long idAuthor
) {
    public DTOCourseAnswer(Course course) {
        this(course.getName(), course.getCategory(), course.getAuthor_id());
    }
}


