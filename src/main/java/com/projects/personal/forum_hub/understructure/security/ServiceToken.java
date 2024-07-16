package com.projects.personal.forum_hub.understructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import com.auth0.jwt.interfaces.JWTVerifier;
import com.projects.personal.forum_hub.dto.user.DTOUser;
import com.projects.personal.forum_hub.dto.user.DTOUserAnswer;
import com.projects.personal.forum_hub.models.User;
import com.projects.personal.forum_hub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;

@Service
public class ServiceToken {

    @Value("${api.security.secret}")
    private String keySecret;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(User user) throws JWTCreationException{
        try {
            Algorithm algorithm = Algorithm.HMAC256(keySecret);
            String token = JWT.create()
                    .withIssuer("forum-hub")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Unexpected error, try again later ", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token is null");
        }
        try {
            Algorithm  algorithm = Algorithm.HMAC256(keySecret);
            return JWT.require(algorithm)
                    .withIssuer("forum-hub")
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token is invalid");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}
