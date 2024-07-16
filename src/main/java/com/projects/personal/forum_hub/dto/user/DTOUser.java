package com.projects.personal.forum_hub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DTOUser(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank @Size(min = 8)
        String password,
        @NotNull
        String profile
) {
}
