package com.projects.personal.forum_hub.understructure.errors;
/*
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;*/
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrors {

    @ExceptionHandler({EntityNotFoundException.class,IllegalArgumentException.class})
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NotExist.class)
    public ResponseEntity errorNotExist(NotExist ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erro400(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(validationError::new).toList());
    }

    /*@ExceptionHandler({JWTCreationException.class, JWTVerificationException.class})
    public ResponseEntity errorJWT(JWTCreationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }*/

    private record validationError(String key, String message) {
        public validationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}