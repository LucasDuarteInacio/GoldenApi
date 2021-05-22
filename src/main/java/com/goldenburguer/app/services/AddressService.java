package com.goldenburguer.app.services;

import com.goldenburguer.app.dto.AddressDTO;
import com.goldenburguer.app.entities.Address;
import com.goldenburguer.app.entities.Neighborhood;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class AddressService {

  @Autowired
  private CustomerService customerService;

  private final AddressRepository repository;
  private final NeighborhoodService neighborhoodService;


  public Address newAddress(Address address) {
    Neighborhood neighborhood = neighborhoodService.findById(address.getNeighborhood().getId());
    address.setStatus(true);
    address.setNeighborhood(neighborhood);
    return repository.save(address);
  }

  public Address findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::addressNotFound);
  }

  public Address findAddressByCustomer(Integer idCustomer) {
    customerService.findById(idCustomer);
    return repository.findAddressByCustomer(idCustomer);
  }

  public Address updateAddress(Address addressObj) {

    findById(addressObj.getId());
    Neighborhood neighborhood = neighborhoodService.findById(addressObj.getNeighborhood().getId());

    Address address = Address.builder()
            .id(addressObj.getId())
            .city(addressObj.getCity())
            .complement(addressObj.getComplement())
            .latitude(addressObj.getLatitude())
            .longitude(addressObj.getLongitude())
            .neighborhood(neighborhood)
            .number(addressObj.getNumber())
            .reference(addressObj.getReference())
            .state(addressObj.getState())
            .street(addressObj.getStreet())
            .zipCode(addressObj.getZipCode())
            .build();

    return repository.save(address);
  }

}
