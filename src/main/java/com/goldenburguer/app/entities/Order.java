package com.goldenburguer.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goldenburguer.app.entities.enums.OrderProviderEnum;
import com.goldenburguer.app.entities.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "orders")
public class Order {

  @Id
  @ApiModelProperty(value = "Order id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer orderNumber;

  @NotNull
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "checkout_id")
  @ApiModelProperty(value = "The checkout where this order was opened")
  private Checkout checkout;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "customer_id")
  @ApiModelProperty(value = "Customer responsible for this order")
  private Customer customer;

  @Enumerated(EnumType.STRING)
  @ApiModelProperty(value = "Order status")
  private OrderStatusEnum status;

  @NotNull
  @ApiModelProperty(value = "Moment the order was created")
  private LocalDateTime createdAt;

  @ApiModelProperty(value = "Moment when the order preparation was finalized")
  private LocalDateTime finishedAt;

  @ApiModelProperty(value = "Order subtotal")
  private BigDecimal subtotal;

  @ApiModelProperty(value = "Order delivery tax")
  private BigDecimal deliveryTax;

  @NotNull
  @ApiModelProperty(value = "Order total price")
  private BigDecimal totalPrice;

  @NotNull
  @Enumerated(EnumType.STRING)
  @ApiModelProperty(value = "Order source")
  private OrderProviderEnum provider;

  @OneToMany(mappedBy = "order")
  @ApiModelProperty(value = "List of order items")
  private List<Item> items;
}
