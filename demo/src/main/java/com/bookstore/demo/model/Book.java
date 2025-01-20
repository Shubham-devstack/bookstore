package com.bookstore.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @Pattern(regexp = "^\\d{3}-\\d{10}$", message = "ISBN must be in the format 000-0000000000")
    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @NotBlank(message = "Book Title must not be blank")
    @Size(min = 1, max = 50, message = "Book Title must be between 5 and 50 characters")
    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @NotNull(message = "Authors must not be null and already exist")
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    private List<Author> authors;

    @Positive(message = "Year must be a positive number")
    @Min(value = 1000, message = "Year must be a 4-digit number")
    @Max(value = 9999, message = "Year must be a 4-digit number")
    @NotNull(message = "Year must not be null")
    private int year;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be a positive number")
    private double price;

    @NotBlank(message = "Genre must not be blank")
    @Size(min = 3, max = 20, message = "Genre must be between 3 and 20 characters")
    private String genre;

    @PrePersist
    @PreUpdate
    private void preInsertUpdate() {
        this.title = this.title.toLowerCase();
        this.genre = this.genre.toLowerCase();
    }
}
