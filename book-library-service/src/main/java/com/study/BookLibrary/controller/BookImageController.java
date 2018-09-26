package com.study.BookLibrary.controller;

import com.study.BookLibrary.dto.output.BookImageOutputDTO;
import com.study.BookLibrary.service.BookImageService;

import io.swagger.annotations.Api;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(
    tags = "BookImage",
    description = "the book image API"
)
@CrossOrigin
@RestController
@RequestMapping("/library/book-image")
public class BookImageController {

  private BookImageService bookImageService;

  @Autowired
  public BookImageController(BookImageService bookImageService) {
    this.bookImageService = bookImageService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookImageOutputDTO>> getAllBookImage() {
    return new ResponseEntity<>(bookImageService.getAllBookImage(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookImageOutputDTO> getBookImageById(@PathVariable Long id) {
    return new ResponseEntity<>(bookImageService.getBookImageById(id), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void saveBookImage(@RequestParam(value = "imageFile") MultipartFile imageFile,
      @RequestPart(value = "bookId") String bookId) {
    bookImageService.saveBookImage(imageFile, bookId);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void modifyBookImage(@PathVariable Long id, MultipartFile imageFile, String bookId) {
    bookImageService.modifyBookImage(id, imageFile, bookId);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteBookImage(@PathVariable Long id) {
    bookImageService.deleteBookImage(id);
  }

  @GetMapping(value = "/book-id/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookImageOutputDTO> getBookImageByBookId(@PathVariable Long bookId) {
    return new ResponseEntity<>(bookImageService.getBookImageByBookId(bookId), HttpStatus.OK);
  }
}