package com.bookstore.books.bookstore.repository;

import com.bookstore.books.bookstore.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthors_Name(String name);
}
