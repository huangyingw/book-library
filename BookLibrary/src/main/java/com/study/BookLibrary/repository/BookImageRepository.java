package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.BookImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends JpaRepository<BookImageEntity, Long> {

}
