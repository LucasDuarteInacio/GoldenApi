package com.goldenburguer.app.repositories;

import com.goldenburguer.app.entities.Order;

import java.util.List;


import com.goldenburguer.app.entities.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  @Query("SELECT  o  FROM Order o WHERE NOT o.status =?1")
  List<Order> findOrderByStatusIsNot(OrderStatusEnum status);
}
