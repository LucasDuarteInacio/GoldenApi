package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

  @Query("select a from Address a join Customer c on a.id = c.address where c.id=?1")
  Address findAddressByCustomer(Integer idCustomer);
}
