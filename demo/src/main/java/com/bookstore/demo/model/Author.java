package com.bookstore.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author name must not be blank")
    @Size(min = 5, max = 100, message = "Author name must be between 10 and 100 characters")
    private String name;

    @Past(message = "Birthday must be in the past")
    @NotNull(message = "Birthday must not be null")
    private Date birthday;

    public Author(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Author(String name) {
        this.name = name;
    }

    @PrePersist
    @PreUpdate
    private void preInsertUpdate() {
        this.name = this.name.toLowerCase();
    }
}
