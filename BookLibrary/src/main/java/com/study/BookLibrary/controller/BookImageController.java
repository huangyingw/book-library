package com.study.BookLibrary.controller;

import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.BookImageEntity;
import com.study.BookLibrary.error.InternalServerErrorException;
import com.study.BookLibrary.service.BookImageService;
import java.io.IOException;
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
  public ResponseEntity<List<BookImageEntity>> getAllBookImage() {
    return new ResponseEntity<>(bookImageService.getAllBookImage(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookImageEntity> getBookImageById(@PathVariable Long id) {
    return new ResponseEntity<>(bookImageService.getBookImageById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addBookImage(@RequestParam(value = "file")MultipartFile file, @RequestPart(value = "id")String id) {
    log.debug("My Log: " + file.getOriginalFilename());
    log.debug("My Log: " + id);
    bookImageService.addBookImage(convertToEntity(file, Long.parseLong(id)));
  }

  private BookImageEntity convertToEntity(MultipartFile file, Long id) {
    BookImageEntity entity = new BookImageEntity();
    try {
      entity.setImageDataFiles(file.getBytes());
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage());
    }
    BookEntity bookEntity = new BookEntity();
    bookEntity.setId(id);
    entity.setBook(bookEntity);
    return entity;
  }
}
