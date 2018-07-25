package com.study.BookLibrary.controller;

import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.service.AuthorService;
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
@RequestMapping("/library/author")
public class AuthorController {
  private AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
    return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable Long id) {
    return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addAuthor(@RequestBody AuthorEntity authorEntity) {
    authorService.addAuthor(authorEntity);
  }
}
