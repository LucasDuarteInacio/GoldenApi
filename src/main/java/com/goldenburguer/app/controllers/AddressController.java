package com.goldenburguer.app.controllers;

import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.AddressDTO;
import com.goldenburguer.app.entities.Address;
import com.goldenburguer.app.services.AddressService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Api(tags = SwaggerConfig.ADDRESS)
public class AddressController {


    private final AddressService service;

    @GetMapping(value = "customer/{id}")
    public ResponseEntity<Address> findByCustomer (@PathVariable Integer id){
        Address address = service.findAddressByCustomer(id);
        return ResponseEntity.ok().body(address);
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress (@RequestBody AddressDTO addressDTO){
        Address address = service.updateCustomer(addressDTO);
        return ResponseEntity.ok().body(address);
    }
}
