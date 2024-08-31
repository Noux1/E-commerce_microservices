package com.noux.paymentservice.entity;

import com.noux.paymentservice.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "payment_table")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotNull
    private String reference;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "paymnet_method")
    private PaymentMethod paymentMethod;

    @NotNull
    private Long orderId;

    @NotNull
    @CreatedDate
    @Column(updatable = false, nullable = false , name = "created_date")
    private LocalDateTime createdDate;

    @NotNull
    @LastModifiedDate
    @Column(insertable = false , name = "last_modified_date" , nullable = false)
    private LocalDateTime lastModifiedDate;

}
