package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.BookImageEntity;
import java.util.Optional;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends JpaRepository<BookImageEntity, Long> {

  Optional<BookImageEntity> findByFileName(String fileName);
}
