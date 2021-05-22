package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.OptionDTO;
import com.goldenburguer.app.entities.Option;
import com.goldenburguer.app.entities.Product;
import com.goldenburguer.app.services.OptionService;
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
@RequestMapping("option")
@Api(tags = SwaggerConfig.OPTION)
public class OptionController {

  private final OptionService service;

  @GetMapping
  @ApiOperation(value = "List all options")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all options"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Option>> listOptions() {
    List<Option> options = service.listAllOptions();
    return ResponseEntity.ok().body(options);
  }

  @GetMapping("active")
  @ApiOperation(value = "List all options actived")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "List all options actived"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<List<Option>> listOptionsActived() {
    List<Option> options = service.listAllOptionsActive();
    return ResponseEntity.ok().body(options);
  }

  @PostMapping
  @ApiOperation(value = "Register new option")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Register new option"),
        @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        @ApiResponse(code = 500, message = "An exception was generated"),
      })
  public ResponseEntity<Option> createOption(@RequestBody Option option) {
    Option newOption = service.newOption(option);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(newOption.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newOption);
  }

  @PutMapping
  @ApiOperation(value = "Edit Option")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Edit Option"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found")
      })
  public ResponseEntity<Option> editOption(@RequestBody OptionDTO optionDTO) {
    Option option = service.updateOption(optionDTO);
    return ResponseEntity.ok().body(option);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete Option by id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Delete Option"),
        @ApiResponse(code = 404, message = "Not found")
      })
  public ResponseEntity<Option> deleteOption(@PathVariable Integer id) {
    service.deleteOption(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("reactive/{idOption}")
  @ApiOperation(value = "Reactive a product")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Reactive product by id"),
                  @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                  @ApiResponse(code = 500, message = "An exception was generated"),
          })
  public ResponseEntity<Product> reactiveProduct(@PathVariable Integer idOption) {
    service.reactiveOption(idOption);
    return ResponseEntity.ok().build();
  }
}
