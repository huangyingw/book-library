package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.AuthorInputDTO;
import com.study.BookLibrary.dto.output.AuthorOutputDTO;
import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.mapper.Mapper;
import com.study.BookLibrary.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private AuthorRepository authorRepository;

  private final Mapper mapper = new Mapper();

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
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

  public void addAuthor(AuthorInputDTO authorInputDTO) {
    Optional<AuthorEntity> author = authorRepository
        .findByFirstNameAndLastName(authorInputDTO.getFirstName(), authorInputDTO.getLastName());
    if (!author.isPresent()) {
      authorRepository.save(mapper.map(authorInputDTO, AuthorEntity.class));
    } else {
      throw new ConflictException("Can not create author, it is already exist.",
          ServiceErrorCode.ALREADY_EXIST);
    }
  }
}
