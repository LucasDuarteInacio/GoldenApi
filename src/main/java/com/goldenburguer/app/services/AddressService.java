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

  public Address updateCustomer(AddressDTO addressDTO) {

    findById(addressDTO.getId());
    Neighborhood neighborhood = neighborhoodService.findById(addressDTO.getIdNeighborhood());

    Address address = Address.builder()
            .id(addressDTO.getId())
            .city(addressDTO.getCity())
            .complement(addressDTO.getComplement())
            .latitude(addressDTO.getLatitude())
            .longitude(addressDTO.getLongitude())
            .neighborhood(neighborhood)
            .number(addressDTO.getNumber())
            .reference(addressDTO.getReference())
            .state(addressDTO.getState())
            .street(addressDTO.getStreet())
            .zipCode(addressDTO.getZipCode())
            .build();

    return repository.save(address);
  }

}
