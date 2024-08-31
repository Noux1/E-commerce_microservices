package com.noux.productservice.request.product.DTO;

import com.noux.productservice.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {
    private Long id ;

    @NotNull
    private String name ;

    @NotNull
    private String Description ;

    @NotNull
    private double availableQuantity ;

    @NotNull
    private BigDecimal price ;

    private Long categoryId;

}
