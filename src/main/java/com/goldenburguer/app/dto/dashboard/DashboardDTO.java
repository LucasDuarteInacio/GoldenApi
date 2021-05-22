package com.goldenburguer.app.dto.dashboard;


import com.goldenburguer.app.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private Integer customerQuantity;
    private Integer customerActivedQuantity;
    private Integer orderQuantitySevenDays;
    private Integer orderQuantityThirtyDays;
    private Integer orderQuantityAll;
    private Product bestSellingProductSevenDays;
    private Product bestSellingProductThirtyDays;
    private Product bestSellingProductAll;
    private BigDecimal averageOrderPriceSevenDays;
    private BigDecimal averageOrderPriceThirtyDays;
    private BigDecimal averageOrderPriceAll;

}
