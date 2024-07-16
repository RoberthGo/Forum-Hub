package com.projects.personal.forum_hub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DTOUserLogin(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
