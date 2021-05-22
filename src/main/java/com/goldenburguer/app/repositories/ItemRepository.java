package com.goldenburguer.app.repositories;

import com.goldenburguer.app.dto.dashboard.QntSellingProductDTO;
import com.goldenburguer.app.entities.Address;
import com.goldenburguer.app.entities.Item;

import java.util.List;

import lombok.var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {


  @Query(
      value = "SELECT i.product_id  product, COUNT(i.quantity) quantity\n"
          + "FROM item i\n"
          + "JOIN product p ON p.id = i.product_id\n"
          + "GROUP BY i.product_id\n"
          + "ORDER BY quantity",nativeQuery = true)
  List<Object> qntSellingProductDTO();



}
