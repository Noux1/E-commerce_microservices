package com.noux.orderservice.request.OrderDetail.mapper;

import com.noux.orderservice.entity.Order;
import com.noux.orderservice.entity.OrderDetail;
import com.noux.orderservice.request.OrderDetail.DTO.OrderDetailRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailRequestMapper {

    public OrderDetail requestMapping(OrderDetailRequest orderDetailRequest) {
        return OrderDetail.builder()
                .id(orderDetailRequest.getId())
                .qte(orderDetailRequest.getQte())
                .order(Order.builder()
                        .id(orderDetailRequest.getOrderId())
                        .build()
                )
                .productId(orderDetailRequest.getProductId())
                .build();
    }
}
