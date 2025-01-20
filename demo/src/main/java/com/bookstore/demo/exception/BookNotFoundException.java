package com.bookstore.demo.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn, String type) {
        super(String.format("Book with %s '%s' not found", type, isbn));
    }
}
