package com.goldenburguer.app.dto.dashboard;

import com.goldenburguer.app.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QntSellingProductDTO {


    private String product;
    private BigInteger quantity;

}
