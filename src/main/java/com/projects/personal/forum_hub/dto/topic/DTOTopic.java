package com.projects.personal.forum_hub.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long author,
        @NotNull
        Long course
) {
}
