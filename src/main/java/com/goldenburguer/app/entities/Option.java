package com.goldenburguer.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

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
public class Option {

  @Id
  @ApiModelProperty(value = "Option id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ApiModelProperty(value = "Option name")
  private String name;

  @ApiModelProperty(value = "Option price")
  private BigDecimal price;

  @ApiModelProperty(value = "Option status")
  private Boolean status;

  @JsonIgnore
  @ManyToMany(mappedBy = "options")
  @ApiModelProperty(value = "Optionals of an item")
  private List<Item> items;
}
