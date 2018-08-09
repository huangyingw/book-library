package com.study.BookLibrary.controller;

import com.study.BookLibrary.dto.input.BookInputDTO;
import com.study.BookLibrary.dto.output.BookOutputDTO;
import com.study.BookLibrary.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/library/book")
public class BookController {

  private BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookOutputDTO>> getAllBooks() {
    return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookOutputDTO> getBookById(@PathVariable Long id) {
    return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addBook(@RequestBody BookInputDTO bookInputDTO) {
    bookService.addBook(bookInputDTO);
  }
}
