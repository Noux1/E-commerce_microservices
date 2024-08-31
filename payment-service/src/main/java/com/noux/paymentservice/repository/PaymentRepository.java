package com.noux.paymentservice.repository;

import com.noux.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
