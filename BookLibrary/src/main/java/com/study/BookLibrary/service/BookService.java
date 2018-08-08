package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.BookInputDTO;
import com.study.BookLibrary.dto.output.BookOutputDTO;
import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.CategoryEntity;
import com.study.BookLibrary.error.InternalServerErrorException;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.mapper.Mapper;
import com.study.BookLibrary.repository.AuthorRepository;
import com.study.BookLibrary.repository.BookRepository;

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

  private final Mapper mapper = new Mapper();

  @Autowired
  public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
      CategoryRepository categoryRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
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

  public void addBook(BookInputDTO bookInputDTO) {
    Optional<AuthorEntity> author = authorRepository.findById(bookInputDTO.getAuthorId());
    Optional<CategoryEntity> category = categoryRepository.findById(bookInputDTO.getCategoryId());
    if (author.isPresent()) {
      if (category.isPresent()) {
        Optional<BookEntity> book = bookRepository
            .findByTitleAndAuthorAndCategory(bookInputDTO.getTitle(), author.get(), category.get());
        if (!book.isPresent()) {
          BookEntity bookEntity = mapper.map(bookInputDTO, BookEntity.class);
          bookEntity.setAuthor(author.get());
          bookEntity.setCategory(category.get());
          bookRepository.save(bookEntity);
        } else {
          throw new ConflictException("Can not create book, it is already exist.",
              ServiceErrorCode.ALREADY_EXIST);
        }
      } else {
        throw new NotFoundException("Can not create book without category.",
            ServiceErrorCode.NOT_FOUND);
      }
    } else {
      throw new NotFoundException("Can not create book without author.",
          ServiceErrorCode.NOT_FOUND);
    }
  }
}
