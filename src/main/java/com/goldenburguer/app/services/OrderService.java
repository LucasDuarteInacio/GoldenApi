package com.goldenburguer.app.services;

import com.goldenburguer.app.dto.OrderDTO;
import com.goldenburguer.app.entities.Item;
import com.goldenburguer.app.entities.Order;
import com.goldenburguer.app.entities.enums.OrderStatusEnum;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class  OrderService {

  private final OrderRepository repository;
  private final CustomerService customerService;
  private final ItemService itemService;
  private final CheckoutService checkoutService;

  public Order findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::orderNotFound);
  }

  public List<Order> listAllOrders() {
    return repository.findAll();
  }

  public List<Order> listAllOrdersActived() {
    return repository.findOrderByStatusIsNot(OrderStatusEnum.CANCELED);
  }

  public Order newOrder(OrderDTO orderDTO) {

    Order newOrder = Order.builder()
            .customer(customerService.findById(orderDTO.getCustomerId()))
            .checkout(checkoutService.findById(orderDTO.getCheckoutId()))
            .status(OrderStatusEnum.IN_PREPARATION)
            .createdAt(LocalDateTime.now())
            .provider(orderDTO.getProvider())
            .deliveryTax(orderDTO.getDeliveryTax())
            .build();


    List<Item> items =
            orderDTO.getItems().stream()
            .map(item -> itemService.itemFromDto(item))
            .collect(Collectors.toList());

    newOrder.setItems(items);

    BigDecimal subtotal = calculateSubTotal(items);
    newOrder.setSubtotal(subtotal);
    newOrder.setTotalPrice(subtotal.add(orderDTO.getDeliveryTax()));

    itemService.saveItem(newOrder);

    checkoutService.currentBalanceCheckout(newOrder.getCheckout().getId());

    return newOrder;
  }

  public Order updateOrder(OrderDTO orderDTO) {

    findById(orderDTO.getId());

    Order order = Order.builder()
            .id(orderDTO.getId())
            .checkout(checkoutService.findById(orderDTO.getCheckoutId()))
            .customer(customerService.findById(orderDTO.getCustomerId()))
            .deliveryTax(orderDTO.getDeliveryTax())
            .provider(orderDTO.getProvider())
            .build();


    checkoutService.currentBalanceCheckout(order.getCheckout().getId());

    return order;

  }

  public void deleteOrder(Integer id) {
    Order order = findById(id);
    order.setStatus(OrderStatusEnum.CANCELED);
    repository.save(order);
  }

  public BigDecimal calculateSubTotal(List<Item> items) {

    BigDecimal subtotal = BigDecimal.ZERO;
    for (Item item : items) {
      subtotal = subtotal.add(item.getSubTotal());
    }

    return subtotal;
  }
}
