package com.bookstore.books.bookstore.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Author {
    @Id
    private Long id;
    private String name;
    private String birthday;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
}