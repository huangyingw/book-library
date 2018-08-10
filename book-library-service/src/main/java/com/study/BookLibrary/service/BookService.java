package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.BookInputDTO;
import com.study.BookLibrary.dto.output.BookOutputDTO;
import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.CategoryEntity;
import com.study.BookLibrary.entity.PublisherEntity;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.mapper.Mapper;
import com.study.BookLibrary.repository.AuthorRepository;
import com.study.BookLibrary.repository.BookRepository;

import com.study.BookLibrary.repository.PublisherRepository;
import java.util.List;
import java.util.Optional;

import com.study.BookLibrary.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private BookRepository bookRepository;
  private AuthorRepository authorRepository;
  private CategoryRepository categoryRepository;
  private PublisherRepository publisherRepository;

  private final Mapper mapper = new Mapper();

  @Autowired
  public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
      CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
    this.publisherRepository = publisherRepository;
  }

  public List<BookOutputDTO> getAllBooks() {
    return mapper.mapToList(bookRepository.findAll(), BookOutputDTO.class);
  }

  public BookOutputDTO getBookById(Long id) {
    BookEntity bookEntity = bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Book with id=" + id + " is not exist.",
            ServiceErrorCode.NOT_FOUND));
    return mapper.map(bookEntity, BookOutputDTO.class);
  }

  public void saveBook(BookInputDTO bookInputDTO) {
    Optional<AuthorEntity> author = authorRepository.findById(bookInputDTO.getAuthorId());
    if (!author.isPresent()) {
      throw new NotFoundException("Can not create book without author.",
          ServiceErrorCode.NOT_FOUND);
    }
    Optional<CategoryEntity> category = categoryRepository.findById(bookInputDTO.getCategoryId());
    if (!category.isPresent()) {
      throw new NotFoundException("Can not create book without category.",
          ServiceErrorCode.NOT_FOUND);
    }
    Optional<PublisherEntity> publisher = publisherRepository
        .findById(bookInputDTO.getPublisherId());
    if (!publisher.isPresent()) {
      throw new NotFoundException("Can not create book without publisher.",
          ServiceErrorCode.NOT_FOUND);
    }
    Optional<BookEntity> book = bookRepository
        .findByTitleAndAuthorAndCategory(bookInputDTO.getTitle(), author.get(), category.get());
    if (book.isPresent()) {
      throw new ConflictException("Can not create book, it is already exist.",
          ServiceErrorCode.ALREADY_EXIST);
    }

    BookEntity bookEntity = mapper.map(bookInputDTO, BookEntity.class);
    bookEntity.setAuthor(author.get());
    bookEntity.setCategory(category.get());
    bookEntity.setPublisher(publisher.get());
    bookRepository.save(bookEntity);
  }

  public void modifyBook(Long id, BookInputDTO bookInputDTO) {
    Optional<BookEntity> book = bookRepository.findById(id);
    if(!book.isPresent())
      throw new NotFoundException("Can not modify non-existing book.", ServiceErrorCode.NOT_FOUND);

    Optional<AuthorEntity> author = authorRepository.findById(bookInputDTO.getAuthorId());
    if(!author.isPresent())
      throw new NotFoundException("Can not modify book without author.", ServiceErrorCode.NOT_FOUND);
    Optional<CategoryEntity> category = categoryRepository.findById(bookInputDTO.getCategoryId());
    if(!category.isPresent())
      throw new NotFoundException("Can not modify book without category.", ServiceErrorCode.NOT_FOUND);
    Optional<PublisherEntity> publisher = publisherRepository.findById(bookInputDTO.getPublisherId());
    if(!publisher.isPresent())
      throw new NotFoundException("Can not modify book without publisher.", ServiceErrorCode.NOT_FOUND);

    mapper.map(bookInputDTO, book.get());
    book.get().setAuthor(author.get());
    book.get().setCategory(category.get());
    book.get().setPublisher(publisher.get());
    bookRepository.save(book.get());
  }

  public void deleteBook(Long id) {
    if(!bookRepository.existsById(id))
      throw new NotFoundException("Can not delete non-existing book.", ServiceErrorCode.NOT_FOUND);
    bookRepository.deleteById(id);
  }
}
