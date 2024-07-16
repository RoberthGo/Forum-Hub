package com.projects.personal.forum_hub.dto.course;

import jakarta.validation.constraints.NotBlank;

public record DTOCourse(
        @NotBlank
        String name,
        @NotBlank
        String category
) {
}
