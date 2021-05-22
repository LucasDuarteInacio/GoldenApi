package com.goldenburguer.app.dto;

import com.goldenburguer.app.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

  private Integer id;
  private String name;
  private Address address;
}
