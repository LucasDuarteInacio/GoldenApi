package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Product;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  Optional<Product> findProductByNameAndStatusTrue(String name);

  List<Product> findProductByStatusTrue();
}
