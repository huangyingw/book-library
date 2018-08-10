package com.study.BookLibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book_image")
public class BookImageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(min = 5)
  private String fileName;

  @Column(nullable = false)
  @Lob
  private byte[] imageDataFiles;

  @OneToOne(optional = false)
  @JoinColumn(name = "book_id")
  private BookEntity book;

  public BookImageEntity() {
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte[] getImageDataFiles() {
    return imageDataFiles;
  }

  public void setImageDataFiles(byte[] imageDataFiles) {
    this.imageDataFiles = imageDataFiles;
  }

  public BookEntity getBook() {
    return book;
  }

  public void setBook(BookEntity book) {
    this.book = book;
  }

  @Override
  public String toString() {
    return "BookImageEntity{" +
        "id=" + id +
        ", fileName='" + fileName + '\'' +
        '}';
  }
}
