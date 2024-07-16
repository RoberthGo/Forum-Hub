package com.projects.personal.forum_hub.repository;

import com.projects.personal.forum_hub.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    boolean existsByTitleAndMessage(String author, String message);
}
