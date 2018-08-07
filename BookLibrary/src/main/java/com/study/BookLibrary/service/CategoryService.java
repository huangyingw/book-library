package com.study.BookLibrary.service;

import com.study.BookLibrary.entity.CategoryEntity;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryEntity> getAllCategory() {
    return categoryRepository.findAll();
  }

  public CategoryEntity getCategoryById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Category with id=" + id + " is not exist"));
  }

  public void addCategory(CategoryEntity categoryEntity) {
    categoryRepository.save(categoryEntity);
  }

}
