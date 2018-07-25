package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<BookEntity> getAllBooks() {
    return bookRepository.findAll();
  }

  public BookEntity getBookById(Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id=" + id + " is not exist."));
  }

  public void addBook(BookEntity bookEntity) {
    bookRepository.save(bookEntity);
  }
}
