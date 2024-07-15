package com.projects.personal.forum_hub.dto.topic;

import jakarta.validation.constraints.NotBlank;

public record DTOTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String author,
        @NotBlank
        String course
) {
}
