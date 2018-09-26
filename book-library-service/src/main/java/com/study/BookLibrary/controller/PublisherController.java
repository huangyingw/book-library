package com.study.BookLibrary.controller;

import com.study.BookLibrary.dto.input.PublisherInputDTO;
import com.study.BookLibrary.dto.output.PublisherOutputDTO;
import com.study.BookLibrary.service.PublisherService;

import io.swagger.annotations.Api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(
    tags = "Publisher",
    description = "the publisher API"
)
@CrossOrigin
@RestController
@RequestMapping("/library/publisher")
public class PublisherController {

  private PublisherService publisherService;

  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublisherOutputDTO>> getAllPublisher() {
    return new ResponseEntity<>(publisherService.getAllPublisher(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublisherOutputDTO> getPublisherById(@PathVariable Long id) {
    return new ResponseEntity<>(publisherService.getPublisherById(id), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void savePublisher(@RequestBody PublisherInputDTO publisherInputDTO) {
    publisherService.savePublisher(publisherInputDTO);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void modifyPublisher(@PathVariable Long id,
      @RequestBody PublisherInputDTO publisherInputDTO) {
    publisherService.modifyPublisher(id, publisherInputDTO);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deletePublisher(@PathVariable Long id) {
    publisherService.deletePublisher(id);
  }
}
