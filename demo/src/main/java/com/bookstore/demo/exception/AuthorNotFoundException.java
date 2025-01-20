package com.bookstore.demo.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String name) {
        super(String.format("Author with name '%s' not found", name));
    }
}
