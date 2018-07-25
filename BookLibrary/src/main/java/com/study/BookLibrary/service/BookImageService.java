package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.BookImageEntity;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.BookImageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookImageService {
  private BookImageRepository bookImageRepository;

  @Autowired
  public BookImageService(BookImageRepository bookImageRepository) {
    this.bookImageRepository = bookImageRepository;
  }

  public List<BookImageEntity> getAllBookImage() {
    return bookImageRepository.findAll();
  }

  public BookImageEntity getBookImageById(Long id) {
    return bookImageRepository.findById(id).orElseThrow(() -> new NotFoundException("Book image with id=" + id + " is not exist."));
  }

  public void addBookImage(BookImageEntity bookImageEntity) {
    bookImageRepository.save(bookImageEntity);
  }
}
