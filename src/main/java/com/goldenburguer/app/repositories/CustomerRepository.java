package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Customer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  List<Customer> findCustomerByStatusTrue();
}
