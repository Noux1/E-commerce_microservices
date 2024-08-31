package com.noux.orderservice.response.OrderDetail.mapper;

import com.noux.orderservice.entity.OrderDetail;
import com.noux.orderservice.response.OrderDetail.DTO.OrderDetailResponse;
import org.springframework.stereotype.Component;

@Component

public class OrderDetailResponseMapper {
    public OrderDetailResponse responseMapping(OrderDetail orderDetail){
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setId(orderDetail.getId());
        orderDetailResponse.setQte(orderDetail.getQte());
        return orderDetailResponse;
    }


}
