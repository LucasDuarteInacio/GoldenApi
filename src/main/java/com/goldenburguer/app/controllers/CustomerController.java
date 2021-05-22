package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.CustomerDTO;
import com.goldenburguer.app.entities.Customer;
import com.goldenburguer.app.services.CustomerService;
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
@RequestMapping("customer")
@Api(tags = SwaggerConfig.CUSTOMER)
public class CustomerController {

  private final CustomerService service;

  @GetMapping
  @ApiOperation(value = "List all customers")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all customers"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Customer>> listCustomers() {
    List<Customer> customers = service.listAllCustomers();
    return ResponseEntity.ok().body(customers);
  }

  @GetMapping("active")
  @ApiOperation(value = "List all customers actived")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all customers actived"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Customer>> listCustomersActived() {
    List<Customer> customers = service.listAllCustomersActive();
    return ResponseEntity.ok().body(customers);
  }

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Find customer by Id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all customers"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
    Customer customers = service.findById(id);
    return ResponseEntity.ok().body(customers);
  }

  @PostMapping
  @ApiOperation(value = "Register new customer")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Register new customer"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exceptions was generated"),
      })
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer newCustomer = service.newCustomer(customer);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(newCustomer.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newCustomer);
  }

  @DeleteMapping("/{idCustomer}")
  @ApiOperation(value = "delete a customer")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Delete customer by id"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer idCustomer) {
    service.deleteCustomer(idCustomer);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  @ApiOperation(value = "Update a customer")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Update a customer"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Customer> updateProduct(@RequestBody CustomerDTO customerDTO) {
    Customer customer = service.updateCustomer(customerDTO);
    return ResponseEntity.ok().body(customer);
  }
}
