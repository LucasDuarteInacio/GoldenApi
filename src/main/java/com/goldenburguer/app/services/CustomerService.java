package com.goldenburguer.app.services;

import com.goldenburguer.app.dto.CustomerDTO;
import com.goldenburguer.app.entities.Address;
import com.goldenburguer.app.entities.Customer;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.CustomerRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

  private final CustomerRepository repository;
  private final AddressService addressService;

  public Customer findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::customerNotFound);
  }

  public List<Customer> listAllCustomers() {
    return repository.findAll();
  }

  public List<Customer> listAllCustomersActive() {
    return repository.findCustomerByStatusTrue();
  }

  public Customer newCustomer(Customer customer) {
    Address address = addressService.newAddress(customer.getAddress());
    customer.setAddress(address);
    customer.setStatus(true);
    return repository.save(customer);
  }

  public Customer updateCustomer(CustomerDTO customerDTO) {
    Customer customer = findById(customerDTO.getId());
    Address address = addressService.updateAddress(customerDTO.getAddress());
    customer.setAddress(address);
    customer.setName(customerDTO.getName());

    return repository.save(customer);
  }

  public void deleteCustomer(Integer id) {

    Customer customer = findById(id);
    customer.setStatus(false);

    repository.save(customer);
  }
}
