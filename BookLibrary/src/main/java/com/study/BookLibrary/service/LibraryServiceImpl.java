package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.AuthorRepository;
import com.study.BookLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id=" + id + " is not exist."));
    }

    @Override
    public void addBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }



    @Override
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorEntity getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id=" + id + " is not exist."));
    }

    @Override
    public void addAuthor(AuthorEntity authorEntity) {
        authorRepository.save(authorEntity);
    }
}
