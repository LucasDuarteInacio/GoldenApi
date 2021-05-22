package com.goldenburguer.app.config;

import com.goldenburguer.app.entities.*;
import com.goldenburguer.app.entities.enums.OrderProviderEnum;
import com.goldenburguer.app.entities.enums.OrderStatusEnum;
import com.goldenburguer.app.repositories.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class Instantiation implements CommandLineRunner {

  private final CustomerRepository customerRepository;
  private final CheckoutRepository checkoutRepository;
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final OptionRepository optionRepository;
  private final AddressRepository addressRepository;
  private final OrderRepository orderRepository;
  private final ItemRepository itemRepository;
  private final NeighborhoodRepository neighborhoodRepository;

  @Override
  public void run(String... args) throws Exception {

    Neighborhood neighborhood = neighborhoodRepository.save(new Neighborhood(null,"Agua Branca",true));

    Address address =
        addressRepository.save(
            new Address(
                null,
                "180",
                "Ribamar",
                "Casa",
                "9999-99",
                null,
                null,
                null,
                    neighborhood,
                "Contagem",
                "MG",
                true));

    Customer customer = customerRepository.save(new Customer(null, "Pedrin", address, true, null));
    Checkout checkout =
        checkoutRepository.save(
            new Checkout(
                null, LocalDateTime.now(), null, new BigDecimal("42"), new BigDecimal("25"), null));

    Category category = categoryRepository.save(new Category(null, "Hamburguer", true, null));
    Category category2 = categoryRepository.save(new Category(null, "Refrigerante", true, null));

    Product product =
        productRepository.save(
            new Product(
                null,
                "Xtudão",
                "hamburguer top com tudo dentro",
                true,
                null,
                BigDecimal.TEN,
                BigDecimal.TEN,
                "1",
                category,
                null));

    Product product2 =
        productRepository.save(
            new Product(
                null,
                "Coca",
                "Refri chei de borbulha bem refrescante",
                true,
                null,
                BigDecimal.valueOf(5.0),
                BigDecimal.valueOf(5.0),
                "1",
                category2,
                null));

    Option option =
        optionRepository.save(new Option(null, "Bacon", BigDecimal.valueOf(5.0), true, null));
    Option option2 =
        optionRepository.save(new Option(null, "Milho", BigDecimal.valueOf(2.0), true, null));

    List<Option> options = Arrays.asList(option, option2);

    Order order =
        new Order(
            null,
            null,
            checkout,
            customer,
            OrderStatusEnum.DELIVERED,
            LocalDateTime.now(),
                LocalDateTime.now().minusDays(2),
            new BigDecimal("37.00"),
            new BigDecimal("5.00"),
            new BigDecimal("42.00"),
            OrderProviderEnum.WHATSAPP,
            null);

    Order order1 =
            new Order(
                    null,
                    null,
                    checkout,
                    customer,
                    OrderStatusEnum.DELIVERED,
                    LocalDateTime.now(),
                    LocalDateTime.now().minusDays(15),
                    new BigDecimal("37.00"),
                    new BigDecimal("5.00"),
                    new BigDecimal("42.00"),
                    OrderProviderEnum.WHATSAPP,
                    null);

    Order order2 =
            new Order(
                    null,
                    null,
                    checkout,
                    customer,
                    OrderStatusEnum.DELIVERED,
                    LocalDateTime.now(),
                    LocalDateTime.now().minusDays(40),
                    new BigDecimal("37.00"),
                    new BigDecimal("5.00"),
                    new BigDecimal("42.00"),
                    OrderProviderEnum.WHATSAPP,
                    null);

    Item item = new Item(null, 2, new BigDecimal("27.00"), product, options, "Sem picles", order);
    Item item2 = new Item(null, 2, new BigDecimal("10.00"), product2, null, null, order);

    Item item3 = new Item(null, 2, new BigDecimal("27.00"), product, options, "Sem picles", order1);
    Item item4 = new Item(null, 2, new BigDecimal("10.00"), product2, null, null, order1);

    Item item5 = new Item(null, 2, new BigDecimal("27.00"), product, options, "Sem picles", order2);
    Item item6 = new Item(null, 2, new BigDecimal("10.00"), product2, null, null, order2);

    itemRepository.saveAll(Arrays.asList(item, item2,item3,item4,item5,item6));

    System.out.println();
    System.out.println();

    System.out.println(customer.toString());
    System.out.println();

    System.out.println(checkout.toString());
    System.out.println();

    System.out.println(category.toString());
    System.out.println();

    System.out.println(category2.toString());
    System.out.println();

    System.out.println(product.toString());
    System.out.println();

    System.out.println(product2.toString());
    System.out.println();

    System.out.println(option.toString());
    System.out.println();

    System.out.println(option2.toString());
    System.out.println();

    System.out.println(order.toString());
    System.out.println();
  }
}
