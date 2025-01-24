package com.bookstore.books.bookstore.payload;

import com.bookstore.books.bookstore.data.Book;

import java.util.Map;

public class RequestPayload {

    private String action; // The operation to perform (e.g., "add", "update", "delete", "find")
    private Book book; // The book data for "add" or "update" actions
    private String isbn; // The ISBN for "update" or "delete" actions
    private Map<String, String> filters; // Filters for "find" action (e.g., title, author)

    // Default constructor
    public RequestPayload() {
    }

    // Getters and setters
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return "RequestPayload{" +
                "action='" + action + '\'' +
                ", book=" + book +
                ", isbn='" + isbn + '\'' +
                ", filters=" + filters +
                '}';
    }
}

