package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.AuthorEntity;
import com.study.BookLibrary.entity.BookEntity;
import com.study.BookLibrary.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

  Optional<BookEntity> findByTitleAndAuthorAndCategory(String title, AuthorEntity author,
      CategoryEntity category);
}
