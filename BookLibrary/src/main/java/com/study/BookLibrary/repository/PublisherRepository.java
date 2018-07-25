package com.study.BookLibrary.repository;

import com.study.BookLibrary.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

}
