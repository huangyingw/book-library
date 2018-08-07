package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.BookInputDTO;
import com.study.BookLibrary.dto.output.BookOutputDTO;
import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.CategoryEntity;
import com.study.BookLibrary.error.InternalServerErrorException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.AuthorRepository;
import com.study.BookLibrary.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.study.BookLibrary.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private BookRepository bookRepository;
  private AuthorRepository authorRepository;
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
      CategoryRepository categoryRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<BookOutputDTO> getAllBooks() {
    return convertToListDto(bookRepository.findAll());
  }

  public BookOutputDTO getBookById(Long id) {
    BookEntity bookEntity = bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Book with id=" + id + " is not exist."));
    return convertToDto(bookEntity);
  }

  public void addBook(BookInputDTO bookInputDTO) {
    Optional<AuthorEntity> author = authorRepository.findById(bookInputDTO.getAuthorId());
    Optional<CategoryEntity> category = categoryRepository.findById(bookInputDTO.getCategoryId());
    if (author.isPresent()) {
      if (category.isPresent()) {
        Optional<BookEntity> book = bookRepository
            .findByTitleAndAuthorAndCategory(bookInputDTO.getTitle(), author.get(), category.get());
        if (!book.isPresent()) {
          BookEntity bookEntity = convertToEntity(bookInputDTO);
          bookEntity.setAuthor(author.get());
          bookEntity.setCategory(category.get());
          System.out.println(category.get());
          bookRepository.save(bookEntity);
        } else {
          //TODO Specify new Exception
          throw new InternalServerErrorException("Can not create book, it is already exist.");
        }
      } else {
        //TODO Specify new Exception
        throw new InternalServerErrorException("Can not create book without category.");
      }
    } else {
      //TODO Specify new Exception
      throw new InternalServerErrorException("Can not create book without author.");
    }
  }

  private BookEntity convertToEntity(BookInputDTO bookInputDTO) {
    return modelMapper.map(bookInputDTO, BookEntity.class);
  }

  private BookOutputDTO convertToDto(BookEntity bookEntity) {
    return modelMapper.map(bookEntity, BookOutputDTO.class);
  }

  private List<BookOutputDTO> convertToListDto(List<BookEntity> books) {
    return books.stream().map(entity -> modelMapper.map(entity, BookOutputDTO.class))
        .collect(Collectors.toList());
  }
}
