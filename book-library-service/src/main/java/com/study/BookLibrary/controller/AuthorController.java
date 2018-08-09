package com.study.BookLibrary.controller;

import com.study.BookLibrary.dto.input.AuthorInputDTO;
import com.study.BookLibrary.dto.output.AuthorOutputDTO;
import com.study.BookLibrary.service.AuthorService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/library/author")
public class AuthorController {

  private AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AuthorOutputDTO>> getAllAuthors() {
    return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthorOutputDTO> getAuthorById(@PathVariable Long id) {
    return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void saveAuthor(@RequestBody AuthorInputDTO authorInputDTO) {
    authorService.saveAuthor(authorInputDTO);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void modifyAuthor(@PathVariable Long id, @RequestBody AuthorInputDTO authorInputDTO) {
    authorService.modifyAuthor(id, authorInputDTO);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
  }
}
