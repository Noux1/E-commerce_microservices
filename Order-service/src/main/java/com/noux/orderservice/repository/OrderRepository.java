package com.noux.orderservice.repository;

import com.noux.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {
}
