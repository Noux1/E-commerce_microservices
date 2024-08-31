package com.noux.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotNull
    @Column(nullable = false , unique = true)
    private String name ;

    @NotNull
    @Column(nullable = false)
    private String Description ;

    @NotNull
    @Column(name = "available_quantity" ,nullable = false)
    private double availableQuantity ;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



}
