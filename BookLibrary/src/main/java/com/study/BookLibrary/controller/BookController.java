package com.study.BookLibrary.controller;

import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/book")
public class BookController {
  private BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookEntity>> getAllBooks() {
    return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
    return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addBook(@RequestBody BookEntity bookEntity) {
    bookService.addBook(bookEntity);
  }
}
