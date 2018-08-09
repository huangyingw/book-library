package com.study.BookLibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "publisher")
public class PublisherEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(min = 1)
  private String name;

  public PublisherEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "PublisherEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}