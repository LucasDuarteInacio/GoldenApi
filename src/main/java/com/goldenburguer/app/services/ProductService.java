package com.goldenburguer.app.services;

import com.goldenburguer.app.dto.ProductDTO;
import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.entities.Product;
import com.goldenburguer.app.exceptions.BadRequestException;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.ProductRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository repository;
  private final CategoryService categoryService;

  public Product findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::productNotFound);
  }

  public Product getProductById(Integer id) {
    Product product = findById(id);
    if(product.getStatus() == false){
      throw NotFoundException.productNotFound();
    }else{
      return product;
    }
  }

  public List<Product> listAllProducts() {
    return repository.findAll();
  }

  public List<Product> listAllProductsActive() {
    return repository.findProductByStatusTrue();
  }

  public Product newProduct(Product product) {

    Category category = categoryService.findById(product.getCategory().getId());

    boolean productIsPresent =
        repository.findProductByNameAndStatusTrue(product.getName()).isPresent();

    if (productIsPresent) {
      throw BadRequestException.existingProduct();
    }

   product.setCategory(category);
    product.setStatus(true);


    return repository.save(product);
  }

  public Product updateProduct(Product product) {
    findById(product.getId());
    Category category = categoryService.findById(product.getCategory().getId());

    Product productUpdated =
        Product.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .status(true)
            .image(product.getImage())
            .price(product.getPrice())
            .originalPrice(product.getOriginalPrice())
            .serving(product.getServing())
            .category(category)
            .build();

    return repository.save(productUpdated);
  }

  public void deleteProduct(Integer id) {
    Product product = findById(id);
    product.setStatus(false);
    repository.save(product);
  }

  public void reactiveProduct(Integer id) {
    Product product = findById(id);
    product.setStatus(true);
    repository.save(product);
  }
}
