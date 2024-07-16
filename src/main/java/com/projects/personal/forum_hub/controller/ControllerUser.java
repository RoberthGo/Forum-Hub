package com.projects.personal.forum_hub.controller;

import com.projects.personal.forum_hub.dto.token.TokenData;
import com.projects.personal.forum_hub.dto.user.DTOUser;
import com.projects.personal.forum_hub.dto.user.DTOUserAnswer;
import com.projects.personal.forum_hub.models.User;
import com.projects.personal.forum_hub.service.ServiceUser;
import com.projects.personal.forum_hub.understructure.errors.NotExist;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
public class ControllerUser {
    private ServiceUser serviceUser = new ServiceUser();

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DTOUserAnswer> registerUser(@RequestBody @Valid DTOUser userAns, UriComponentsBuilder uriComponentsBuilder) throws NotExist {
        return serviceUser.registerUser(userAns,uriComponentsBuilder);
    }

    @PostMapping("/login")
    //@Transactional
    public ResponseEntity<TokenData> loginUser(@RequestBody @Valid DTOUser userAns) {
        return serviceUser.verifyUser(userAns);
    }


    @GetMapping
    public ResponseEntity<Page<DTOUserAnswer>> getUser(@PageableDefault(size = 5, sort = {"username"}) Pageable page) {
        return serviceUser.getAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOUserAnswer> getUser(@PathVariable Long id) {;
        return serviceUser.getByID(id);
    }

    /*@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<User> updateUser(@PathVariable Long id) {
        return serviceUser.deleteByID(id);
    }*/
}
