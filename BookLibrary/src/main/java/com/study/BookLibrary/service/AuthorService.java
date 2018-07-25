package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
  private AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<AuthorEntity> getAllAuthors() {
    return authorRepository.findAll();
  }

  public AuthorEntity getAuthorById(Long id) {
    return authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id=" + id + " is not exist."));
  }

  public void addAuthor(AuthorEntity authorEntity) {
    authorRepository.save(authorEntity);
  }
}
