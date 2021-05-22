package com.goldenburguer.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @ApiModelProperty(value = "Address id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ApiModelProperty(value = "Number of the house")
  private String number;

  @NotNull
  @ApiModelProperty(value = "Name of the street")
  private String street;

  @ApiModelProperty(value = "Address complement")
  private String complement;


  @ApiModelProperty(value = "Zip code of this address")
  private String zipCode;

  @ApiModelProperty(value = "Latitude of this address")
  private String latitude;

  @ApiModelProperty(value = "Longitude of this address")
  private String longitude;

  @ApiModelProperty(value = "Address reference")
  private String reference;

  @NotNull
  @ApiModelProperty(value = "Neighborhood")
  private String neighborhood;

  @NotNull
  @ApiModelProperty(value = "City")
  private String city;

  @ApiModelProperty(value = "State")
  private String state;

  @ApiModelProperty(value = "Category status")
  private Boolean status;

}
