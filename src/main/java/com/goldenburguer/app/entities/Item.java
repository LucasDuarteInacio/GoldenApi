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
public class Item {

  @Id
  @ApiModelProperty(value = "ProductGroup id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ApiModelProperty(value = "Product quantity")
  private Integer quantity;

  @NotNull
  @ApiModelProperty(value = "Subtotal of products")
  private BigDecimal subTotal;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "product_id")
  @ApiModelProperty(value = "Product chosen by the customer")
  private Product product;

  @ManyToMany
  @JoinTable(
      name = "item_option",
      joinColumns = @JoinColumn(name = "item_id"),
      inverseJoinColumns = @JoinColumn(name = "option_id"))
  @ApiModelProperty(value = "All options contained in this item")
  private List<Option> options;

  @ApiModelProperty(value = "Order notes")
  private String notes;

  @JsonIgnore
  @JoinColumn(name = "order_id")
  @ManyToOne(cascade = CascadeType.PERSIST)
  @ApiModelProperty(value = "Customer Order")
  private Order order;
}
