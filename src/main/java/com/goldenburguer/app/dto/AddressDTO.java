package com.goldenburguer.app.dto;

import lombok.*;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

  private Integer id;
  private String number;
  private String street;
  private String complement;
  private String zipCode;
  private String latitude;
  private String longitude;
  private String reference;
  private String neighborhood;
  private String city;
  private String state;

}
