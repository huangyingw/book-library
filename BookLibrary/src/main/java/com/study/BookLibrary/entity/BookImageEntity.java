package com.study.BookLibrary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BookImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private BookEntity book;
  private byte[] imageDataFiles;

  public BookImageEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BookEntity getBook() {
    return book;
  }

  public void setBook(BookEntity book) {
    this.book = book;
  }

  public byte[] getImageDataFiles() {
    return imageDataFiles;
  }

  public void setImageDataFiles(byte[] imageDataFiles) {
    this.imageDataFiles = imageDataFiles;
  }
}
