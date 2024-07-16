package com.projects.personal.forum_hub.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOUpdateCourse(
        String name,
        String category,
        boolean publicCourse
) {
}
