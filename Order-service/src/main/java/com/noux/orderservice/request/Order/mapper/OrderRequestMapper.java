package com.noux.orderservice.request.Order.mapper;

import com.noux.orderservice.entity.Order;
import com.noux.orderservice.entity.OrderDetail;
import com.noux.orderservice.request.Order.DTO.OrderRequest;
import com.noux.orderservice.request.OrderDetail.DTO.OrderDetailRequest;
import com.noux.orderservice.request.OrderDetail.mapper.OrderDetailRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class OrderRequestMapper {
    public Order requestMapping(OrderRequest orderRequest) {
        if (orderRequest == null){
            return null;
        }
        return Order.builder()
                .id(orderRequest.getId())
                .reference(orderRequest.getReference())
                .paymentMethod(orderRequest.getPaymentMethod())
                .clientId(orderRequest.getClientId())
                .totalAmount(orderRequest.getTotalAmount())
                .build();
    }

}
