package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.AuthorInputDTO;
import com.study.BookLibrary.dto.output.AuthorOutputDTO;
import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.converter.Mapper;
import com.study.BookLibrary.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  private final Mapper mapper;

  public AuthorService(AuthorRepository authorRepository,
      Mapper mapper) {
    this.authorRepository = authorRepository;
    this.mapper = mapper;
  }

  public List<AuthorOutputDTO> getAllAuthors() {
    return mapper.mapToList(authorRepository.findAll(), AuthorOutputDTO.class);
  }

  public AuthorOutputDTO getAuthorById(Long id) {
    AuthorEntity authorEntity = authorRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Author with id=" + id + " is not exist.",
            ServiceErrorCode.NOT_FOUND));
    return mapper.map(authorEntity, AuthorOutputDTO.class);
  }

  public void saveAuthor(AuthorInputDTO authorInputDTO) {
    Optional<AuthorEntity> author = authorRepository
        .findByFirstNameAndLastName(authorInputDTO.getFirstName(), authorInputDTO.getLastName());
    if (author.isPresent()) {
      throw new ConflictException("Can not create author, it is already exist.",
          ServiceErrorCode.ALREADY_EXIST);
    }
    authorRepository.save(mapper.map(authorInputDTO, AuthorEntity.class));
  }

  public void modifyAuthor(Long id, AuthorInputDTO authorInputDTO) {
    Optional<AuthorEntity> author = authorRepository.findById(id);
    if (!author.isPresent()) {
      throw new NotFoundException("Can not modify non-existing author.",
          ServiceErrorCode.NOT_FOUND);
    }

    mapper.map(authorInputDTO, author.get());
    authorRepository.save(author.get());
  }

  public void deleteAuthor(Long id) {
    if (!authorRepository.existsById(id)) {
      throw new NotFoundException("Can not delete non-existing author.",
          ServiceErrorCode.NOT_FOUND);
    }
    authorRepository.deleteById(id);
  }
}
