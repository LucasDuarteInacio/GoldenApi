package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Category;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Optional<Category> findCategoryByNameAndStatusTrue(String name);

  List<Category> findCategoryByStatusTrue();
}
