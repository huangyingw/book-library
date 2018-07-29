package com.study.BookLibrary.controller;

import com.study.BookLibrary.entity.PublisherEntity;
import com.study.BookLibrary.service.PublisherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/library/publisher")
public class PublisherController {

  private PublisherService publisherService;

  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublisherEntity>> getAllPublisher() {
    return new ResponseEntity<>(publisherService.getAllPublisher(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublisherEntity> getPublisherById(@PathVariable Long id) {
    return new ResponseEntity<>(publisherService.getPublisherById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addBook(@RequestBody PublisherEntity publisherEntity) {
    publisherService.addPublisher(publisherEntity);
  }
}
