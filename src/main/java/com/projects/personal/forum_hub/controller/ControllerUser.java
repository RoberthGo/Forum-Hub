package com.projects.personal.forum_hub.controller;

import com.projects.personal.forum_hub.dto.token.TokenData;
import com.projects.personal.forum_hub.dto.user.DTOUser;
import com.projects.personal.forum_hub.dto.user.DTOUserAnswer;
import com.projects.personal.forum_hub.dto.user.DTOUserLogin;
import com.projects.personal.forum_hub.models.User;
import com.projects.personal.forum_hub.service.ServiceToken;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/login")
public class ControllerUser {
    @Autowired
    private ServiceUser serviceUser;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ServiceToken serviceToken;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DTOUserAnswer> registerUser(@RequestBody @Valid DTOUser userAns, UriComponentsBuilder uriComponentsBuilder) throws NotExist {
        return serviceUser.registerUser(userAns,uriComponentsBuilder);
    }

    @PostMapping
    //@Transactional
    public ResponseEntity<TokenData> loginUser(@RequestBody @Valid DTOUserLogin user) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        System.out.println(authToken);
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = serviceToken.generateToken((User) usuarioAutenticado.getPrincipal());
        return  ResponseEntity.ok(new TokenData(JWTtoken));
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
