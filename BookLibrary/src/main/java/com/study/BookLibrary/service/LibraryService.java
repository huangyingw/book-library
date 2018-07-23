package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;

import java.util.List;

public interface LibraryService {
    List<BookEntity> getAllBooks();
    BookEntity getBookById(Long id);
    void addBook(BookEntity bookEntity);


    List<AuthorEntity> getAllAuthors();
    AuthorEntity getAuthorById(Long id);
    void addAuthor(AuthorEntity authorEntity);

}
