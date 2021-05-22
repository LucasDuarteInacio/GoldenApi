package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.ProductDTO;
import com.goldenburguer.app.entities.Product;
import com.goldenburguer.app.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
@Api(tags = SwaggerConfig.PRODUCT)
public class ProductController {

  private final ProductService service;

  @GetMapping
  @ApiOperation(value = "List all products")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all products"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Product>> listProducts() {
    List<Product> products = service.listAllProducts();
    return ResponseEntity.ok().body(products);
  }

  @GetMapping("{idProduct}")
  @ApiOperation(value = "Get Product By Id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Get Product By Id"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Product> getProductById(@PathVariable Integer idProduct) {
    Product product = service.getProductById(idProduct);
    return ResponseEntity.ok().body(product);
  }

  @GetMapping("active")
  @ApiOperation(value = "List all products actived")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "List all products actived"),
                  @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                  @ApiResponse(code = 500, message = "An exception was generated"),
          })
  public ResponseEntity<List<Product>> listProductsActived() {
    List<Product> products = service.listAllProductsActive();
    return ResponseEntity.ok().body(products);
  }

  @PostMapping
  @ApiOperation(value = "Register new product")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Register new product"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product newProduct = service.newProduct(product);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(newProduct.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newProduct);
  }

  @DeleteMapping("/{idProduct}")
  @ApiOperation(value = "delete a product")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Delete product by id"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Product> deleteProduct(@PathVariable Integer idProduct) {
    service.deleteProduct(idProduct);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  @ApiOperation(value = "Update a product")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Update a product"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Product> updateProduct(@RequestBody Product productRequest) {
    Product product = service.updateProduct(productRequest);
    return ResponseEntity.ok().body(product);
  }

  @PostMapping("reactive/{idProduct}")
  @ApiOperation(value = "Reactive a product")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Reactive product by id"),
                  @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                  @ApiResponse(code = 500, message = "An exception was generated"),
          })
  public ResponseEntity<Product> reactiveProduct(@PathVariable Integer idProduct) {
    service.reactiveProduct(idProduct);
    return ResponseEntity.ok().build();
  }
}
