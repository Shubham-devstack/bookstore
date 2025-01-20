package com.bookstore.demo.service;

import com.bookstore.demo.exception.AuthorNotFoundException;
import com.bookstore.demo.exception.EntityAlreadyExists;
import com.bookstore.demo.model.Author;
import com.bookstore.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(String name) {
        return authorRepository.
                findByName(name)
                .orElseThrow(
                        () -> new AuthorNotFoundException(name)
                );
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        if(author == null || author.getName() == null){
            throw new IllegalArgumentException("Author must not be null and shall have a name");
        }
        var authorName = author.getName();
        if(authorRepository.existsByName(authorName.toLowerCase())){
            throw new EntityAlreadyExists("Author", "name", authorName);
        }
        return authorRepository.save(author);
    }

    public boolean authorExistsByName(String name) {
        return authorRepository.existsByName(name);
    }
}