package com.noux.orderservice.service;

import com.noux.orderservice.entity.OrderDetail;
import com.noux.orderservice.repository.OrderDetailRepository;
import com.noux.orderservice.request.OrderDetail.DTO.OrderDetailRequest;
import com.noux.orderservice.request.OrderDetail.mapper.OrderDetailRequestMapper;
import com.noux.orderservice.response.OrderDetail.DTO.OrderDetailResponse;
import com.noux.orderservice.response.OrderDetail.mapper.OrderDetailResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    private final OrderDetailRequestMapper orderDetailRequestMapper;

    private final OrderDetailResponseMapper orderDetailResponseMapper;
    public OrderDetailService(OrderDetailRepository orderDetailRepository , OrderDetailRequestMapper orderDetailRequestMapper ,
                                OrderDetailResponseMapper orderDetailResponseMapper){
        this.orderDetailRepository=orderDetailRepository;
        this.orderDetailRequestMapper=orderDetailRequestMapper;
        this.orderDetailResponseMapper=orderDetailResponseMapper;
    }

    public void saveOrderDetail(OrderDetailRequest request){
        var order = orderDetailRequestMapper.requestMapping(request);
        orderDetailRepository.save(order);
    }

    public List<OrderDetailResponse> findAllByOrderId(Long orderId){
        var order = orderDetailRepository.findAllByOrderId(orderId);
        return order.stream()
                .map(orderDetailResponseMapper::responseMapping)
                .collect(Collectors.toList());

    }
}
