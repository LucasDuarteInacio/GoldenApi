package com.goldenburguer.app.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private Integer id;
  private String name;
  private String description;
  private String image;
  private BigDecimal price;
  private BigDecimal originalPrice;
  private String serving;
  private Integer categoryId;
}
