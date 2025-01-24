package com.bookstore.books.bookstore.api;

import com.bookstore.books.bookstore.payload.RequestPayload;
import com.bookstore.books.bookstore.repository.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SingleEndpoint {
    private final BookService bookService;

    SingleEndpoint(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public String rootApi() {
        return "Welcome to the API root!";
    }


    @PostMapping
    public Object handleRequest(@RequestBody RequestPayload payload) {
        switch (payload.getAction()) {
            case "add":
                return bookService.addBook(payload.getBook());
            case "update":
                return bookService.updateBook(payload.getIsbn(), payload.getBook());
            case "delete":
                bookService.deleteBook(payload.getIsbn());
                return "Book deleted";
            case "find":
                return bookService.findBooks(payload.getFilters());
            default:
                throw new RuntimeException("Invalid action");
        }
    }
}

