package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.CategoryDTO;
import com.goldenburguer.app.dto.NeighborhoodDTO;
import com.goldenburguer.app.entities.Category;
import com.goldenburguer.app.entities.Customer;
import com.goldenburguer.app.entities.Neighborhood;
import com.goldenburguer.app.services.NeighborhoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/neighborhood")
@Api(tags = SwaggerConfig.NEIGHBORHOOD)
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;


    @PostMapping
    @ApiOperation(value = "Register new neighborhood")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Register new neighborhood"),
                    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                    @ApiResponse(code = 500, message = "An exceptions was generated"),
            })
    public ResponseEntity<Neighborhood> createNeighborhood(@RequestBody NeighborhoodDTO neighborhood) {
        Neighborhood newNeighborhood = neighborhoodService.newNeighborhood(neighborhood);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/id")
                        .buildAndExpand(newNeighborhood.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(newNeighborhood);
    }

    @GetMapping("active")
    @ApiOperation(value = "List all Neighborhood actived")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List all Neighborhood actived"),
                    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                    @ApiResponse(code = 500, message = "An exception was generated"),
            })
    public ResponseEntity<List<Neighborhood>> listNeighborhoodsActived() {
        List<Neighborhood> Neighborhoods = neighborhoodService.listAllNeighborhoodsActive();
        return ResponseEntity.ok().body(Neighborhoods);
    }

    @GetMapping()
    @ApiOperation(value = "List all Neighborhood")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List all Neighborhood actived"),
                    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                    @ApiResponse(code = 500, message = "An exception was generated"),
            })
    public ResponseEntity<List<Neighborhood>> listNeighborhoods() {
        List<Neighborhood> Neighborhoods = neighborhoodService.findAll();
        return ResponseEntity.ok().body(Neighborhoods);
    }


    @PutMapping
    @ApiOperation(value = "Update Neighborhood")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Update Neighborhood"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<Neighborhood> updateNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        Neighborhood neighborhood = neighborhoodService.updateNeighborhood(neighborhoodDTO);
        return ResponseEntity.ok().body(neighborhood);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete Neighborhood by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Delete Neighborhood"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<Void> deleteNeighborhood(@PathVariable Integer id) {
        neighborhoodService.disableNeighborhood(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "reactive/{id}")
    @ApiOperation(value = "Reactive Neighborhood by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Reactive Neighborhood"),
                    @ApiResponse(code = 404, message = "Not found")
            })
    public ResponseEntity<Neighborhood> reactiveNeighborhood(@PathVariable Integer id) {
        neighborhoodService.reactiveNeighborhood(id);
        return ResponseEntity.ok().build();
    }


}
