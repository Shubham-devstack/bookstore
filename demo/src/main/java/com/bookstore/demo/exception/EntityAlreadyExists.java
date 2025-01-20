package com.bookstore.demo.exception;

public class EntityAlreadyExists extends RuntimeException {
    public EntityAlreadyExists(String entity, String type, String value) {
        super(String.format("%s with %s '%s' already exists", entity, type, value));
    }
}
