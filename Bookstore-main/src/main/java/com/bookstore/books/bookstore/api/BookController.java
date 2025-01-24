package com.bookstore.books.bookstore.api;

import com.bookstore.books.bookstore.data.Book;
import com.bookstore.books.bookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book book) {
        if (!bookRepository.existsById(isbn)) {
            throw new RuntimeException("Book not found");
        }
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> findBooks(@RequestParam(name = "title",required = false) String title, @RequestParam(name = "author",required = false) String author) {
        if (title != null) {
            return bookRepository.findByTitle(title);
        } else if (author != null) {
            return bookRepository.findByAuthors_Name(author);
        } else {
            return bookRepository.findAll();
        }
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(isbn);
    }
}
