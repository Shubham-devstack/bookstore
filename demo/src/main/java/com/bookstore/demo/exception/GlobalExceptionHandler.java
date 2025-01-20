package com.bookstore.demo.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ProblemDetail handleBookNotFoundException(BookNotFoundException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ProblemDetail handleAuthorNotFoundException(AuthorNotFoundException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
    }

    @ExceptionHandler(InvalidISBNProvided.class)
    public ProblemDetail handleInvalidISBNProvided(InvalidISBNProvided e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400), e.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ProblemDetail handleEntityAlreadyExists(EntityAlreadyExists e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(409), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400), errors.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleEntityValidationExceptions(ConstraintViolationException e) {
        var errors = e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .toList();

        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400), errors.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400), e.getMessage());
    }
}
