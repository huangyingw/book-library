package com.study.BookLibrary.controller;

import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService =  libraryService;
    }

    @RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        return new ResponseEntity<>(libraryService.getAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(libraryService.getBookById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookEntity bookEntity) {
        libraryService.addBook(bookEntity);
    }



    @RequestMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
        return new ResponseEntity<>(libraryService.getAllAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable Long id) {
        System.out.println("Czesc");
        return new ResponseEntity<>(libraryService.getAuthorById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAuthor(@RequestBody AuthorEntity authorEntity) {
        libraryService.addAuthor(authorEntity);
    }
}
