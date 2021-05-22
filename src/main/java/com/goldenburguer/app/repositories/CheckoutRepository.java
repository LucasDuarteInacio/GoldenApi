package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Checkout;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

  Optional<Checkout> findCheckoutByClosedIsNull();

  Optional<Checkout> findCheckoutByOpenedIsNull();
}
