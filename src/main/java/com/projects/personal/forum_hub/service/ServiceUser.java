package com.projects.personal.forum_hub.service;

import com.projects.personal.forum_hub.dto.token.TokenData;
import com.projects.personal.forum_hub.dto.user.DTOUser;
import com.projects.personal.forum_hub.dto.user.DTOUserAnswer;
import com.projects.personal.forum_hub.models.User;
import com.projects.personal.forum_hub.repository.UserRepository;
import com.projects.personal.forum_hub.understructure.errors.NotExist;
import com.projects.personal.forum_hub.understructure.security.ServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ServiceUser {

    private ServiceToken serviceToken = new ServiceToken();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<Page<DTOUserAnswer>> getAll(Pageable page) {
        return ResponseEntity.ok(userRepository.findAll(page).map(DTOUserAnswer::new));
    }

    public ResponseEntity<DTOUserAnswer> getByID(Long id) {
        Optional<User> userDB = Optional.of(userRepository.getReferenceById(id));
        if (!userDB.isPresent()) {
            throw new NotExist("The user does not exist");
        }
        return ResponseEntity.ok(new DTOUserAnswer(userDB.get()));
    }

    public ResponseEntity<TokenData> verifyUser(DTOUser user){
        var authToken = new UsernamePasswordAuthenticationToken(user.email(),user.password());
        System.out.println(authToken);
        var verifiedUser = authenticationManager.authenticate(authToken);
        System.out.println(verifiedUser);
        var JWTtoken = "";//serviceToken.generateToken((User)verifiedUser.getPrincipal() );
        return ResponseEntity.ok(new TokenData(JWTtoken));
    }

    public ResponseEntity<DTOUserAnswer> registerUser(DTOUser userNew, UriComponentsBuilder uriComponentsBuilder){
        if (userRepository.existsByEmail(userNew.email())) {
            throw new NotExist("This email is already in use");
        }
        if (userRepository.existsByUsername(userNew.name())) {
            throw new NotExist("This username is already in use");
        }
        var user = new User(userNew);
        userRepository.save(user);
        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body( new DTOUserAnswer(user));
    }
}
