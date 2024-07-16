package com.projects.personal.forum_hub.dto.user;

import com.projects.personal.forum_hub.models.User;

public record DTOUserAnswer(
        String name,
        String profile
) {
    public DTOUserAnswer(User user) {
        this(user.getUsername(),user.getProfile());
    }
}
