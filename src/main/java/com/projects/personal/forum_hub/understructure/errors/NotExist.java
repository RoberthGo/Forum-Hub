package com.projects.personal.forum_hub.understructure.errors;

public class NotExist extends RuntimeException {
    public NotExist(String message) {
        super(message);
    }
}
