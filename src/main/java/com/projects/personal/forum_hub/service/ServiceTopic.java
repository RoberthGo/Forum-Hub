package com.projects.personal.forum_hub.service;

import com.projects.personal.forum_hub.dto.topic.DTOTopic;
import com.projects.personal.forum_hub.dto.topic.DTOTopicAnswer;
import com.projects.personal.forum_hub.dto.topic.DTOTopicUpdate;
import com.projects.personal.forum_hub.models.Topic;
import com.projects.personal.forum_hub.repository.TopicRepository;
import com.projects.personal.forum_hub.understructure.errors.NotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ServiceTopic {
    @Autowired
    private TopicRepository repositoryTopic;

    public Topic registerTopic(Topic topic) throws NotExist {
        // CAMBIAR POR USER
        if (!repositoryTopic.existsByAuthor(topic.getAuthor())) {
            throw new NotExist("This user does not exist ");
        }
        if (repositoryTopic.existsByAuthorAndMessage(topic.getTitle(), topic.getMessage())) {
            throw new NotExist("Duplicate topic!");
        }
        return repositoryTopic.save(topic);
    }

    public ResponseEntity<DTOTopicAnswer> postTopic(DTOTopic topicAns, UriComponentsBuilder uriComponentsBuilder) {
        var topic = new DTOTopicAnswer(registerTopic(new Topic(topicAns)));
        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.id()).toUri();
        return ResponseEntity.created(url).body(topic);
    }

    public ResponseEntity<Page<DTOTopicAnswer>> getAll(Pageable page) {
        return ResponseEntity.ok(repositoryTopic.findAll(page).map(DTOTopicAnswer::new));
    }

    public ResponseEntity<DTOTopicAnswer> update(Long id, DTOTopicUpdate dataUpdate) throws NotExist {
        Optional<Topic> topicDB = Optional.of(repositoryTopic.getReferenceById(id));
        if (topicDB.isPresent()) {
            var topic = topicDB.get();
            if ( repositoryTopic.existsByAuthorAndMessage(dataUpdate.title(), dataUpdate.message())) {
                throw new NotExist("There is already a topic exactly like this");
            }
            if (dataUpdate.message() != null) {
                topic.setMessage(dataUpdate.message());
            }
            if (dataUpdate.status() != null) {
                topic.setStatus(dataUpdate.status());
            }
            if (dataUpdate.title() != null) {
                topic.setTitle(dataUpdate.title());
            }
            return ResponseEntity.ok(new DTOTopicAnswer(topic));
        }
        return ResponseEntity.notFound().build();
    }

    public DTOTopicAnswer getByID(Long id) throws NotExist {
        Optional<Topic> topicDB = Optional.of(repositoryTopic.getReferenceById(id));
        if (!topicDB.isPresent()) {
            throw new NotExist("The topic does not exist");
        }
        return new DTOTopicAnswer(topicDB.get());
    }

    public ResponseEntity<Topic> deleteByID(Long id) {
        Optional<Topic> topic = Optional.of(repositoryTopic.getReferenceById(id));
        if (topic.isPresent()) {
            repositoryTopic.deleteById(topic.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
