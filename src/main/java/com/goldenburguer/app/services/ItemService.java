package com.goldenburguer.app.services;

import static java.util.Objects.nonNull;

import com.goldenburguer.app.dto.ItemDTO;
import com.goldenburguer.app.dto.OptionDTO;
import com.goldenburguer.app.entities.Item;
import com.goldenburguer.app.entities.Option;
import com.goldenburguer.app.entities.Order;
import com.goldenburguer.app.entities.Product;
import com.goldenburguer.app.repositories.ItemRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

  private final ProductService productService;
  private final OptionService optionService;
  private final ItemRepository repository;

  public Item itemFromDto(ItemDTO itemDto) {

    Product product = productService.findById(itemDto.getProductId());

    List<Option> options = null;

    if (nonNull(itemDto.getOptions()) && itemDto.getOptions().size() > 0) {
      options =
          optionService.findAllById(
              itemDto.getOptions().stream().map(OptionDTO::getId).collect(Collectors.toList()));
    }

    Item item =
        Item.builder()
            .product(product)
            .quantity(itemDto.getQuantity())
            .notes(itemDto.getNotes())
            .options(options)
            .subTotal(calculateSubtotal(product.getPrice(), itemDto.getQuantity(), options))
            .build();

    return item;
  }

  public void deleteItem(List<Item> items){
    repository.deleteAll(items);
  }

  public BigDecimal calculateSubtotal(BigDecimal price, Integer quantity, List<Option> options) {

    BigDecimal subtotal = price.multiply(BigDecimal.valueOf(quantity));
    BigDecimal optSum = BigDecimal.ZERO;
    if (nonNull(options)) {
      for (Option opt : options) {
        optSum = optSum.add(opt.getPrice());
      }
    }
    return subtotal.add(optSum);
  }

  public void saveItem(Order order) {

    order.getItems().stream().forEach(item -> item.setOrder(order));

    repository.saveAll(order.getItems());
  }
}
