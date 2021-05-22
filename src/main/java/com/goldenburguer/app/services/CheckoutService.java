package com.goldenburguer.app.services;

import com.goldenburguer.app.entities.Checkout;
import com.goldenburguer.app.entities.Order;
import com.goldenburguer.app.exceptions.BadRequestException;
import com.goldenburguer.app.exceptions.NotFoundException;
import com.goldenburguer.app.repositories.CheckoutRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutService {

  private final CheckoutRepository repository;

  public Checkout findById(Integer id) {
    return repository.findById(id).orElseThrow(NotFoundException::checkoutNotFound);
  }

  public Checkout checkoutOpened(Checkout obj) {

    boolean checkoutClosedIsPresent = repository.findCheckoutByClosedIsNull().isPresent();

    if (checkoutClosedIsPresent) {
      throw BadRequestException.checkoutOpened();
    }

    Checkout checkout = new Checkout();

    checkout.setOpened(LocalDateTime.now());
    checkout.setBalance(BigDecimal.valueOf(0));

    checkout.setChange((null != obj.getChange()) ? obj.getChange() : BigDecimal.valueOf(0));

    return repository.save(checkout);
  }

  public Checkout checkoutclosed(Integer CheckoutId) {

    Checkout checkout = findById(CheckoutId);

    if (null == checkout.getClosed()) {
      checkout.setClosed(LocalDateTime.now());
    } else {
      throw BadRequestException.checkoutClosed();
    }

    return repository.save(checkout);
  }

  public Checkout currentBalanceCheckout(Integer idCheckout) {

    Checkout checkout = findById(idCheckout);

    BigDecimal orderBalance = BigDecimal.ZERO;
    for (Order order : checkout.getOrders()) {
      orderBalance = orderBalance.add(order.getTotalPrice());
    }
    checkout.setBalance(orderBalance);

    return repository.save(checkout);
  }

  public Checkout getCheckoutActived() {

    Optional<Checkout> checkoutClosedNotIsPresent = repository.findCheckoutByClosedIsNull();

    if (!checkoutClosedNotIsPresent.isPresent()) {
      throw NotFoundException.checkoutNotFound();
    }

    return checkoutClosedNotIsPresent.get();
  }
}
