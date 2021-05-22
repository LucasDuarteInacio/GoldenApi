package com.goldenburguer.app.dto;

import com.goldenburguer.app.entities.enums.OrderProviderEnum;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

  private Integer id;
  private Integer customerId;
  private Integer checkoutId;
  private List<ItemDTO> items;
  private BigDecimal deliveryTax;
  private BigDecimal subtotal;
  private BigDecimal totalPrice;
  private OrderProviderEnum provider;
  private String notes;
}
