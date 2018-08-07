package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.AuthorInputDTO;
import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.error.InternalServerErrorException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private AuthorRepository authorRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<AuthorEntity> getAllAuthors() {
    return authorRepository.findAll();
  }

  public AuthorEntity getAuthorById(Long id) {
    return authorRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Author with id=" + id + " is not exist."));
  }

  public void addAuthor(AuthorInputDTO authorInputDTO) {
    Optional<AuthorEntity> author = authorRepository
        .findByFirstNameAndLastName(authorInputDTO.getFirstName(), authorInputDTO.getLastName());
    if (!author.isPresent()) {
      authorRepository.save(convertToEntity(authorInputDTO));
    } else {
      throw new InternalServerErrorException("Can not create book, it is already exist.");
    }
  }

  private AuthorEntity convertToEntity(AuthorInputDTO authorInputDTO) {
    return modelMapper.map(authorInputDTO, AuthorEntity.class);
  }
}
