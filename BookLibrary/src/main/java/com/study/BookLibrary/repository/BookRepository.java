package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
