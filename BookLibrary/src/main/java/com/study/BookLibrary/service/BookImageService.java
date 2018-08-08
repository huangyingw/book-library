package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.BookImageEntity;
import com.study.BookLibrary.error.InternalServerErrorException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.repository.BookImageRepository;

import com.study.BookLibrary.repository.BookRepository;
import java.io.IOException;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookImageService {

  private BookImageRepository bookImageRepository;
  private BookRepository bookRepository;

  @Autowired
  public BookImageService(BookImageRepository bookImageRepository, BookRepository bookRepository) {
    this.bookImageRepository = bookImageRepository;
    this.bookRepository = bookRepository;
  }

  public List<BookImageEntity> getAllBookImage() {
    return bookImageRepository.findAll();
  }

  public BookImageEntity getBookImageById(Long id) {
    return bookImageRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Book image with id=" + id + " is not exist.",
            ServiceErrorCode.NOT_FOUND));
  }

  public void addBookImage(MultipartFile imageFile, String bookId) {
    Optional<BookEntity> bookEntity = bookRepository.findById(Long.parseLong(bookId));
    if(!bookEntity.isPresent())
      throw new NotFoundException("Can not create book_image without book.", ServiceErrorCode.NOT_FOUND);

    BookImageEntity bookImageEntity = new BookImageEntity();
    try {
      bookImageEntity.setImageDataFiles(imageFile.getBytes());
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage(), ServiceErrorCode.CONNECTION_FAILED);
    }
    bookImageEntity.setFileName(imageFile.getOriginalFilename());
    bookImageEntity.setBook(bookEntity.get());
    bookImageRepository.save(bookImageEntity);
  }
}
