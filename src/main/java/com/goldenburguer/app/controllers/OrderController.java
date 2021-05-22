package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.OrderDTO;
import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.entities.Order;
import com.goldenburguer.app.services.OrderService;
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
@RequestMapping("/order")
@Api(tags = SwaggerConfig.ORDER)
public class OrderController {

  private final OrderService service;

  @GetMapping
  @ApiOperation(value = "List all order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all order"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Order>> listOrders() {
    List<Order> orders = service.listAllOrders();
    return ResponseEntity.ok().body(orders);
  }

  @GetMapping("active")
  @ApiOperation(value = "List all actived order")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "List all actived order"),
                  @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                  @ApiResponse(code = 500, message = "An exception was generated"),
          })
  public ResponseEntity<List<Order>> listOrdersActived() {
    List<Order> orders = service.listAllOrdersActived();
    return ResponseEntity.ok().body(orders);
  }

  @PostMapping
  @ApiOperation(value = "Register new order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Register new order"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Order> createOrder(@RequestBody OrderDTO order) {
    Order newOrder = service.newOrder(order);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(newOrder.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newOrder);
  }

  @PutMapping
  @ApiOperation(value = "Update Order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Update Order"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found")
      })
  public ResponseEntity<Order> updateCategory(@RequestBody OrderDTO orderDTO) {
    Order order = service.updateOrder(orderDTO);
    return ResponseEntity.ok().body(order);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete Order by id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Delete Order"),
        @ApiResponse(code = 404, message = "Not found")
      })
  public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
    service.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
