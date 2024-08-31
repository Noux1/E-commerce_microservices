package com.noux.orderservice.response.Order.mapper;

import com.noux.orderservice.entity.Order;
import com.noux.orderservice.response.Order.DTO.OrderResponse;
import org.springframework.stereotype.Component;

@Component

public class OrderResponseMapper {

    public OrderResponse responseMapping(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setReference(order.getReference());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setClientId(order.getClientId());
        return orderResponse;
    }

}
