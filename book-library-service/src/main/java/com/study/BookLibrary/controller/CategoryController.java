package com.study.BookLibrary.controller;

import com.study.BookLibrary.dto.input.CategoryInputDTO;
import com.study.BookLibrary.dto.output.CategoryOutputDTO;
import com.study.BookLibrary.service.CategoryService;

import io.swagger.annotations.Api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(
    tags = "Category",
    description = "the category API"
)
@CrossOrigin
@RestController
@RequestMapping("/library/category")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CategoryOutputDTO>> getAllCategory() {
    return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryOutputDTO> getCategoryById(@PathVariable Long id) {
    return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void saveCategory(@RequestBody CategoryInputDTO categoryInputDTO) {
    categoryService.saveCategory(categoryInputDTO);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void modifyCategory(@PathVariable Long id,
      @RequestBody CategoryInputDTO categoryInputDTO) {
    categoryService.modifyCategory(id, categoryInputDTO);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}
