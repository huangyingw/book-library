package com.study.BookLibrary.controller;

import com.study.BookLibrary.entity.CategoryEntity;
import com.study.BookLibrary.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/library/category")
public class CategoryController {

  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CategoryEntity>> getAllCategory() {
    return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Long id) {
    return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void addBook(@RequestBody CategoryEntity categoryEntity) {
    categoryService.addCategory(categoryEntity);
  }
}
