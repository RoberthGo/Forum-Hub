/*package com.projects.personal.forum_hub.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
            throw new RuntimeException("Unexpected error, try again later ", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token is null");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm  algorithm = Algorithm.HMAC256(keySecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("forum-hub")
                    .build().verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token is invalid");
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
*/