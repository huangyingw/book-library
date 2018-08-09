package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.PublisherEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

  Optional<PublisherEntity> findByName(String name);
}
