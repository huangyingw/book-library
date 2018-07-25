package com.study.BookLibrary.entity;

import javax.persistence.*;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private AuthorEntity author;
    @ManyToOne
    private CategoryEntity category;

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
}
