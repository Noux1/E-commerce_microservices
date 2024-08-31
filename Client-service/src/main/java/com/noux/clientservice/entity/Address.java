package com.noux.clientservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    private String houseNumber;

    @NotNull
    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;
}