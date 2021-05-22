package com.goldenburguer.app.services;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

import com.goldenburguer.app.dto.CategoryDTO;
import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.exceptions.BadRequestException;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.CategoryRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryRepository repository;

  public Category findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::categoryNotFound);
  }

  public List<Category> listAllCategories() {
    return repository.findAll();
  }

  public List<Category> listAllCategoriesActive() {
    return repository.findCategoryByStatusTrue();
  }

  public void validateCategory(Category category) {

    if (isNull(category.getName()) || !hasText(category.getName().trim())) {
      throw BadRequestException.invalidCategory();
    }

    boolean categoryIsPresent =
        repository.findCategoryByNameAndStatusTrue(category.getName()).isPresent();

    if (categoryIsPresent) {
      throw BadRequestException.existingCategory();
    }
  }

  public void deleteCategory(Integer id) {

    Category category = findById(id);
    category.setStatus(false);

    repository.save(category);
  }

  public Category newCategory(Category category) {

    category.setName(category.getName().trim());
    validateCategory(category);
    category.setStatus(true);

    return repository.save(category);
  }

  public Category updateCategory(CategoryDTO categoryDTO) {

    Category updatedCategory = findById(categoryDTO.getId());
    updatedCategory.setName(categoryDTO.getName().trim());
    validateCategory(updatedCategory);

    return repository.save(updatedCategory);
  }

  public void reactiveCategory(Integer id) {

    Category category = findById(id);
    category.setStatus(true);

    repository.save(category);
  }


}
