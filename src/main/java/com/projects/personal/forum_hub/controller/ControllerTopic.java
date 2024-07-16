package com.projects.personal.forum_hub.controller;

import com.projects.personal.forum_hub.dto.topic.DTOTopic;
import com.projects.personal.forum_hub.dto.topic.DTOTopicAnswer;
import com.projects.personal.forum_hub.dto.topic.DTOTopicUpdate;
import com.projects.personal.forum_hub.models.Topic;
import com.projects.personal.forum_hub.service.ServiceTopic;
import com.projects.personal.forum_hub.understructure.errors.NotExist;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping(value = "/topics")
@SecurityRequirement(name = "bearer-key")
public class ControllerTopic {
    @Autowired
    private ServiceTopic serviceTopic;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOTopicAnswer> registerTopic(@RequestBody @Valid DTOTopic topicAns, UriComponentsBuilder uriComponentsBuilder) throws NotExist {
        return serviceTopic.postTopic(topicAns,uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DTOTopicAnswer>> getTopic(@PageableDefault(size = 5, sort = {"creationDate"}) Pageable page) {
        return serviceTopic.getAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOTopicAnswer> getTopic(@PathVariable Long id) {;
        return ResponseEntity.ok(serviceTopic.getByID(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTOTopicAnswer> updateTopic(@PathVariable Long id, @RequestBody DTOTopicUpdate topicUpdate){
        return serviceTopic.update(id,topicUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {
        return serviceTopic.deleteByID(id);
    }
}
