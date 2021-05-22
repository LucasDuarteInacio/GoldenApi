package com.goldenburguer.app.entities;

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
public class Checkout {

  @Id
  @ApiModelProperty(value = "Checkout id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ApiModelProperty(value = "Cash register opening date and time")
  public LocalDateTime opened;

  @ApiModelProperty(value = "Cash register closing date and time")
  public LocalDateTime closed;

  @ApiModelProperty(value = "Balance available at the cash register")
  private BigDecimal balance;

  @NotNull
  @ApiModelProperty(value = "Initial change available at the cash register")
  private BigDecimal change;

  @OneToMany(mappedBy = "checkout")
  @ApiModelProperty(value = "Checkout orders")
  private List<Order> orders;
}
