package com.noux.productservice.response.product.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id ;

    private String name ;

    private String Description ;

    private double availableQuantity ;

    private BigDecimal price ;

    private Long categoryId;

}
