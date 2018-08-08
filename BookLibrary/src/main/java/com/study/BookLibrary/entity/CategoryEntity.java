package com.study.BookLibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  @Size(min = 1)
  private String name;

  public CategoryEntity() {
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
    return "CategoryEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
