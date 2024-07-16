package com.projects.personal.forum_hub.dto.topic;

import com.projects.personal.forum_hub.models.State;
import com.projects.personal.forum_hub.models.Topic;
import java.time.LocalDateTime;

public record DTOTopicAnswer(
        Long id,
        Long author,
        String message,
        Long course,
        String title,
        State status,
        LocalDateTime creationDate
) {
    public DTOTopicAnswer(Topic topic) {
        this(topic.getId(), topic.getAuthor(), topic.getMessage(), topic.getCourse(), topic.getTitle(), topic.getStatus(), topic.getCreationDate());
    }
}
