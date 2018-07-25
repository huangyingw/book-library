package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.BookImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImageEntity, Long> {

}
