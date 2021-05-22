package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.CategoryDTO;
import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Api(tags = SwaggerConfig.CATEGORY)
public class CategoryController {

  private final CategoryService service;

  @GetMapping
  @ApiOperation(value = "List all categories")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all categories"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Category>> listCategories() {
    List<Category> categories = service.listAllCategories();
    return ResponseEntity.ok().body(categories);
  }

  @GetMapping("active")
  @ApiOperation(value = "List all categories actived")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all categories actived"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Category>> listCategoriesActived() {
    List<Category> categories = service.listAllCategoriesActive();
    return ResponseEntity.ok().body(categories);
  }

  @PostMapping
  @ApiOperation(value = "Register new category")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Register new category"),
        @ApiResponse(code = 400, message = "Bad request")
      })
  public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
    Category newCategory = service.newCategory(category);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(newCategory.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newCategory);
  }

  @PutMapping
  @ApiOperation(value = "Update Category")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Update Category"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found")
      })
  public ResponseEntity<Category> editCategory(@RequestBody CategoryDTO categoryDTO) {
    Category category = service.updateCategory(categoryDTO);
    return ResponseEntity.ok().body(category);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete Category by id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Delete Category"),
        @ApiResponse(code = 404, message = "Not found")
      })
  public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
    service.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "reactive/{id}")
  @ApiOperation(value = "Reactive Category by id")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 204, message = "Reactive Category"),
                  @ApiResponse(code = 404, message = "Not found")
          })
  public ResponseEntity<Category> reactiveCategory(@PathVariable Integer id) {
    service.reactiveCategory(id);
    return ResponseEntity.ok().build();
  }
}
