package com.study.BookLibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(min = 1)
  private String title;

  private String description;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private AuthorEntity author;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private PublisherEntity publisherEntity;

  public BookEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AuthorEntity getAuthor() {
    return author;
  }

  public void setAuthor(AuthorEntity author) {
    this.author = author;
  }

  public CategoryEntity getCategory() {
    return category;
  }

  public void setCategory(CategoryEntity category) {
    this.category = category;
  }

  public PublisherEntity getPublisherEntity() {
    return publisherEntity;
  }

  public void setPublisherEntity(PublisherEntity publisherEntity) {
    this.publisherEntity = publisherEntity;
  }

  @Override
  public String toString() {
    return "BookEntity{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", author=" + author +
        ", category=" + category +
        ", publisherEntity=" + publisherEntity +
        '}';
  }
}