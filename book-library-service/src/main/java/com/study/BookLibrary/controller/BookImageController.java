package com.study.BookLibrary.controller;

import com.study.BookLibrary.dto.output.BookImageOutputDTO;
import com.study.BookLibrary.entity.BookImageEntity;
import com.study.BookLibrary.service.BookImageService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/library/book-image")
public class BookImageController {

  private BookImageService bookImageService;

  @Autowired
  public BookImageController(BookImageService bookImageService) {
    this.bookImageService = bookImageService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookImageOutputDTO>> getAllBookImage() {
    return new ResponseEntity<>(bookImageService.getAllBookImage(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookImageOutputDTO> getBookImageById(@PathVariable Long id) {
    return new ResponseEntity<>(bookImageService.getBookImageById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addBookImage(@RequestParam(value = "imageFile") MultipartFile imageFile,
      @RequestPart(value = "bookId") String bookId) {
    bookImageService.addBookImage(imageFile, bookId);
  }
}