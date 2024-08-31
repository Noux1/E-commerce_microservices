package com.noux.clientservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_client")

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name" ,nullable=false)
    private String firstName;

    @NotNull
    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(nullable = false , unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
