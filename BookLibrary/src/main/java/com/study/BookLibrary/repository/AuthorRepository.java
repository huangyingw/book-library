package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

  Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
