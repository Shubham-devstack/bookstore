package com.bookstore.demo.exception;

public class InvalidISBNProvided extends RuntimeException {
    public InvalidISBNProvided(String isbn) {
        super(String.format("Invalid ISBN '%s' provided", isbn));
    }
}
