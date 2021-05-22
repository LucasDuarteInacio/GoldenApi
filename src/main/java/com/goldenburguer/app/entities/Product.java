package com.goldenburguer.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
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
public class Product {

  @Id
  @ApiModelProperty(value = "Product id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ApiModelProperty(value = "Product name")
  private String name;

  @NotNull
  @ApiModelProperty(value = "Product description")
  private String description;

  @ApiModelProperty(value = "Product status")
  private Boolean status;

  @ApiModelProperty(value = "Product image")
  private String image;

  @NotNull
  @ApiModelProperty(value = "Product price")
  private BigDecimal price;

  @NotNull
  @ApiModelProperty(value = "Product original price")
  private BigDecimal originalPrice;

  @ApiModelProperty(value = "Amount of people that product serves")
  private String serving;

  @NotNull
  @ManyToOne
  @ApiModelProperty(value = "Product category")
  @JoinColumn(name = "category_id")
  private Category category;

  @JsonIgnore
  @OneToMany(mappedBy = "product")
  @ApiModelProperty(value = "List of order items")
  private List<Item> items;
}
