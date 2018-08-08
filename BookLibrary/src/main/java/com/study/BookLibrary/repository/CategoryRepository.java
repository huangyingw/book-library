package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  Optional<CategoryEntity> findByName(String name);
}
