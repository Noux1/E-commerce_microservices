package com.noux.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name ;

    @NotNull
    @Column(nullable = false)
    private String Description ;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.REMOVE)
    private List<Product> products ;

}
