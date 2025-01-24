package com.bookstore.books.bookstore.repository;

import com.bookstore.books.bookstore.data.Book;
import com.bookstore.books.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add a new book
    public Book addBook(Book book) {
        if (bookRepository.existsById(book.getIsbn())) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }
        return bookRepository.save(book);
    }

    // Update an existing book
    public Book updateBook(String isbn, Book updatedBook) {
        if (!bookRepository.existsById(isbn)) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
        updatedBook.setIsbn(isbn); // Ensure the correct ISBN is set
        return bookRepository.save(updatedBook);
    }

    // Delete a book
    public void deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
        bookRepository.deleteById(isbn);
    }

    // Find books based on filters
    public List<Book> findBooks(Map<String, String> filters) {
        if (filters == null || filters.isEmpty()) {
            return bookRepository.findAll();
        }

        String title = filters.get("title");
        String author = filters.get("author");

        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthors_Name(author);
        } else {
            return bookRepository.findAll(); // Fallback to return all books
        }
    }
}

