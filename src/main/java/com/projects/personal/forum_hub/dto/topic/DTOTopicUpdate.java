package com.projects.personal.forum_hub.dto.topic;

import com.projects.personal.forum_hub.models.State;

public record DTOTopicUpdate(
        String message,
        String title,
        State status
) {
}

