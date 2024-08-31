package com.noux.productservice.request.category.DTO;

import com.noux.productservice.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    private Long id;

    @NotNull
    private String name ;

    private String Description ;

}
