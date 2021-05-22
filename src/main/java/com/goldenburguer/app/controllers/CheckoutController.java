package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.entities.Checkout;
import com.goldenburguer.app.services.CheckoutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
@Api(tags = SwaggerConfig.CHECKOUT)
public class CheckoutController {

  private final CheckoutService service;

  @PostMapping
  @ApiOperation(value = "Register new checkout")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "New checkout created"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exceptions was generated"),
      })
  public ResponseEntity<Checkout> openedCheckout(@RequestBody Checkout checkout) {
    Checkout newCheckout = service.checkoutOpened(checkout);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(newCheckout.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newCheckout);
  }

  @PutMapping("close/{idCheckout}")
  @ApiOperation(value = "Register new checkout")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Checkout was closed"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exceptions was generated"),
      })
  public ResponseEntity<Checkout> closedCheckout(@PathVariable Integer idCheckout) {
    Checkout closeCheckout = service.checkoutclosed(idCheckout);
    return ResponseEntity.ok().body(closeCheckout);
  }

  @GetMapping("active")
  @ApiOperation(value = "Get actived checkout")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Checkout found"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exceptions was generated"),
      })
  public ResponseEntity<Checkout> getCheckout() {
    Checkout checkout = service.getCheckoutActived();
    return ResponseEntity.ok().body(checkout);
  }
}
