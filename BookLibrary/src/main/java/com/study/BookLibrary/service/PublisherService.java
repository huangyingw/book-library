package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.PublisherEntity;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.PublisherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

  private PublisherRepository publisherRepository;

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public List<PublisherEntity> getAllPublisher() {
    return publisherRepository.findAll();
  }

  public PublisherEntity getPublisherById(Long id) {
    return publisherRepository.findById(id).orElseThrow(() -> new NotFoundException("Publisher with id=" + id + " is not exist."));
  }

  public void addPublisher(PublisherEntity publisherEntity) {
    publisherRepository.save(publisherEntity);
  }

}
