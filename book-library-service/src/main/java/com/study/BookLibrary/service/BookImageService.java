package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.output.BookImageOutputDTO;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.BookImageEntity;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.InternalServerErrorException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.converter.ImageResizer;
import com.study.BookLibrary.converter.Mapper;
import com.study.BookLibrary.repository.BookImageRepository;

import com.study.BookLibrary.repository.BookRepository;
import java.io.IOException;
import java.util.List;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class BookImageService {

  private final BookImageRepository bookImageRepository;
  private final BookRepository bookRepository;
  private final Mapper mapper;
  private final ImageResizer imageResizer;

  public BookImageService(BookImageRepository bookImageRepository, BookRepository bookRepository,
      ImageResizer imageResizer, Mapper mapper) {
    this.bookImageRepository = bookImageRepository;
    this.bookRepository = bookRepository;
    this.imageResizer = imageResizer;
    this.mapper = mapper;
  }

  public List<BookImageOutputDTO> getAllBookImage() {
    return mapper.mapToList(bookImageRepository.findAll(), BookImageOutputDTO.class);
  }

  public BookImageOutputDTO getBookImageById(Long id) {
    BookImageEntity bookImageEntity = bookImageRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Book image with id=" + id + " is not exist.",
            ServiceErrorCode.NOT_FOUND));
    return mapper.map(bookImageEntity, BookImageOutputDTO.class);
  }

  public void saveBookImage(MultipartFile imageFile, String bookId) {
    Optional<BookEntity> book = bookRepository.findById(Long.parseLong(bookId));
    if (!book.isPresent()) {
      throw new NotFoundException("Can not create book_image without book.",
          ServiceErrorCode.NOT_FOUND);
    }

    Optional<BookImageEntity> bookImage = bookImageRepository
        .findByFileName(imageFile.getOriginalFilename());
    if (bookImage.isPresent()) {
      throw new ConflictException("Can not create book_image, it is already exist.",
          ServiceErrorCode.ALREADY_EXIST);
    }

    BookImageEntity bookImageEntity = new BookImageEntity();
    byte[] imageFileBytes;
    try {
      imageFileBytes = imageFile.getBytes();
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage(), ServiceErrorCode.CONNECTION_FAILED);
    }

    try {
      bookImageEntity.setImageDataFiles(imageResizer.bilinearResize(imageFileBytes));
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage(), ServiceErrorCode.CONNECTION_FAILED);
    }

    bookImageEntity.setFileName(imageFile.getOriginalFilename());
    bookImageEntity.setBook(book.get());
    bookImageRepository.save(bookImageEntity);
  }

  public void modifyBookImage(Long id, MultipartFile imageFile, String bookId) {
    Optional<BookImageEntity> bookImage = bookImageRepository.findById(id);
    if (!bookImage.isPresent()) {
      throw new NotFoundException("Can not modify non-existing book_image.",
          ServiceErrorCode.NOT_FOUND);
    }

    Optional<BookEntity> book = bookRepository.findById(Long.parseLong(bookId));
    if (!book.isPresent()) {
      throw new NotFoundException("Can not modify book_image without book.",
          ServiceErrorCode.NOT_FOUND);
    }

    try {
      bookImage.get().setImageDataFiles(imageFile.getBytes());
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage(), ServiceErrorCode.CONNECTION_FAILED);
    }
    bookImage.get().setFileName(imageFile.getOriginalFilename());
    bookImage.get().setBook(book.get());
  }

  public void deleteBookImage(Long id) {
    Optional<BookImageEntity> bookImage = bookImageRepository.findById(id);
    if (!bookImage.isPresent()) {
      throw new NotFoundException("Can not delete non-existing book_image.",
          ServiceErrorCode.NOT_FOUND);
    }
    /**It is required because of cross references, book have to remove reference to bookImage**/
    bookImage.get().getBook().setBookImage(null);
    bookImageRepository.deleteById(id);
  }

  public BookImageOutputDTO getBookImageByBookId(Long bookId) {
    Optional<BookEntity> book = bookRepository.findById(bookId);
    if (!book.isPresent()) {
      throw new NotFoundException("Can not get book_image, valid book_id.",
          ServiceErrorCode.NOT_FOUND);
    }
    Optional<BookImageEntity> bookImage = bookImageRepository.findByBook(book.get());
    if (!bookImage.isPresent()) {
      throw new NotFoundException("Can not get book_image, valid book_id.",
          ServiceErrorCode.NOT_FOUND);
    }
    return mapper.map(bookImage.get(), BookImageOutputDTO.class);
  }
}
