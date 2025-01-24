package com.bookstore.books.bookstore.data;

import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.util.List;

@Entity
public class Book {
    @Id
    private String isbn;
    private String title;
    private int years;
    private double price;
    private String genre;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Author> authors;

    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return years;
    }

    public void setYear(int year) {
        this.years = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
