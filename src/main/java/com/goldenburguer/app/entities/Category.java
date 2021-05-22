package com.goldenburguer.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @ApiModelProperty(value = "category id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ApiModelProperty(value = "Category name")
  private String name;

  @ApiModelProperty(value = "Category status")
  private Boolean status;

  @JsonIgnore
  @OneToMany(mappedBy = "category")
  @ApiModelProperty(value = "Category products")
  private List<Product> products;
}
