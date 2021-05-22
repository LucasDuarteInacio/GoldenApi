package com.goldenburguer.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

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
public class Customer {

  @Id
  @ApiModelProperty(value = "customer id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ApiModelProperty(value = "Customer name")
  private String name;

  @OneToOne
  @ApiModelProperty(value = "Customer address")
  private Address address;

  @ApiModelProperty(value = "Customer status")
  private Boolean status;

  @JsonIgnore
  @OneToMany(mappedBy = "customer")
  @ApiModelProperty(value = "Customer orders")
  private List<Order> orders;
}
