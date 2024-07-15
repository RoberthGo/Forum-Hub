package com.projects.personal.forum_hub.models;
import com.projects.personal.forum_hub.dto.topic.DTOTopic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Enumerated(EnumType.STRING)
    private State status;
    @Setter
    private String title;
    @Setter
    private String course;
    @Setter
    private String message;
    private LocalDateTime creationDate;
    private String author;

    public Topic(DTOTopic topic){
        this.title=topic.title();
        this.message= topic.message();
        this.course=topic.course();
        this.author=topic.author();
        this.creationDate= LocalDateTime.now();
        this.status=State.OPEN;
    }
}
