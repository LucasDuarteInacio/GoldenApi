package com.goldenburguer.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

  private Integer productId;
  private Integer quantity;
  private List<OptionDTO> options;
  private String notes;
}
