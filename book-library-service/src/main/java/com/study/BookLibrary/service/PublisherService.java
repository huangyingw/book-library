package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.PublisherInputDTO;
import com.study.BookLibrary.dto.output.PublisherOutputDTO;
import com.study.BookLibrary.entity.PublisherEntity;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.converter.Mapper;
import com.study.BookLibrary.repository.PublisherRepository;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

  private PublisherRepository publisherRepository;

  private final Mapper mapper = new Mapper();

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public List<PublisherOutputDTO> getAllPublisher() {
    return mapper.mapToList(publisherRepository.findAll(), PublisherOutputDTO.class);
  }

  public PublisherOutputDTO getPublisherById(Long id) {
    PublisherEntity publisherEntity = publisherRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Publisher with id=" + id + " is not exist.",
            ServiceErrorCode.NOT_FOUND));
    return mapper.map(publisherEntity, PublisherOutputDTO.class);
  }

  public void savePublisher(PublisherInputDTO publisherInputDTO) {
    Optional<PublisherEntity> publisher = publisherRepository
        .findByName(publisherInputDTO.getName());
    if (publisher.isPresent()) {
      throw new ConflictException("Can not create publisher, it is already exist.",
          ServiceErrorCode.ALREADY_EXIST);
    }
    publisherRepository.save(mapper.map(publisherInputDTO, PublisherEntity.class));
  }

  public void modifyPublisher(Long id, PublisherInputDTO publisherInputDTO) {
    Optional<PublisherEntity> publisher = publisherRepository.findById(id);
    if (!publisher.isPresent()) {
      throw new NotFoundException("Can not modify non-existing publisher.",
          ServiceErrorCode.NOT_FOUND);
    }

    mapper.map(publisherInputDTO, publisher.get());
    publisherRepository.save(publisher.get());
  }

  public void deletePublisher(Long id) {
    if (!publisherRepository.existsById(id)) {
      throw new NotFoundException("Can not delete non-existing publisher.",
          ServiceErrorCode.NOT_FOUND);
    }
    publisherRepository.deleteById(id);
  }
}