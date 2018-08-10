package com.study.BookLibrary.service;

import com.study.BookLibrary.dto.input.CategoryInputDTO;
import com.study.BookLibrary.dto.output.CategoryOutputDTO;
import com.study.BookLibrary.entity.CategoryEntity;
import com.study.BookLibrary.error.ConflictException;
import com.study.BookLibrary.error.NotFoundException;
import com.study.BookLibrary.error.ServiceErrorCode;
import com.study.BookLibrary.mapper.Mapper;
import com.study.BookLibrary.repository.CategoryRepository;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  private final Mapper mapper = new Mapper();

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryOutputDTO> getAllCategory() {
    return mapper.mapToList(categoryRepository.findAll(), CategoryOutputDTO.class);
  }

  public CategoryOutputDTO getCategoryById(Long id) {
    CategoryEntity categoryEntity = categoryRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Category with id=" + id + " is not exist",
            ServiceErrorCode.NOT_FOUND));
    return mapper.map(categoryEntity, CategoryOutputDTO.class);
  }

  public void saveCategory(CategoryInputDTO categoryInputDTO) {
    Optional<CategoryEntity> category = categoryRepository.findByName(categoryInputDTO.getName());
    if (category.isPresent()) {
      throw new ConflictException("Can not create category, it is already exist.",
          ServiceErrorCode.ALREADY_EXIST);
    }
    categoryRepository.save(mapper.map(categoryInputDTO, CategoryEntity.class));
  }

  public void modifyCategory(Long id, CategoryInputDTO categoryInputDTO) {
    Optional<CategoryEntity> category = categoryRepository.findById(id);
    if (!category.isPresent()) {
      throw new NotFoundException("Can not modify non-existing category.",
          ServiceErrorCode.NOT_FOUND);
    }

    mapper.map(categoryInputDTO, category.get());
    categoryRepository.save(category.get());
  }

  public void deleteCategory(Long id) {
    if (!categoryRepository.existsById(id)) {
      throw new NotFoundException("Can not delete non-existing category.",
          ServiceErrorCode.NOT_FOUND);
    }
    categoryRepository.deleteById(id);
  }
}
