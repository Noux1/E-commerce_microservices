package com.noux.orderservice.entity;

import com.noux.orderservice.enums.OrderStatus;
import com.noux.orderservice.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String reference;

    @NotNull
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(nullable = false)
    private Long clientId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedDate;

}
