package com.bookstore.demo.service;

import com.bookstore.demo.exception.BookNotFoundException;
import com.bookstore.demo.exception.EntityAlreadyExists;
import com.bookstore.demo.exception.InvalidISBNProvided;
import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book addBook(Book book) {
        if (book == null || book.getIsbn() == null) {
            throw new IllegalArgumentException("Book must not be null and shall have a valid ISBN");
        }
        if (bookRepository.existsById(book.getIsbn())) {
            throw new EntityAlreadyExists("Book", "ISBN", book.getIsbn());
        }
        this.validateAuthors(book);
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book book) {
        if (!isbn.matches("\\d{3}-\\d{10}")) {
            throw new InvalidISBNProvided(isbn);
        }

        if (bookRepository.existsById(isbn)) {
            this.validateAuthors(book);
            book.setIsbn(isbn);
            return bookRepository.save(book);
        }
        throw new BookNotFoundException(isbn, "ISBN");
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitle(title.toLowerCase());
    }

    public void deleteBook(String isbn) {
        if(!bookRepository.existsById(isbn)) {
            throw new BookNotFoundException(isbn, "ISBN");
        }
        bookRepository.deleteById(isbn);
    }

    public List<Book> findBooksByAuthorName(String name) {
        Author author = authorService.getAuthor(name.toLowerCase());
        return bookRepository.findByAuthors(author);
    }

    private void validateAuthors(Book book) {
        if(book == null || book.getAuthors() == null || book.getAuthors().isEmpty()){
            throw new IllegalArgumentException("Book must not be null and shall have at least one valid author");
        }
        for(int i = 0; i < book.getAuthors().size(); ++i) {
            Author author = book.getAuthors().get(i);
            author = authorService.getAuthor(author.getName().toLowerCase());
            book.getAuthors().set(i, author);
        }
    }
}
