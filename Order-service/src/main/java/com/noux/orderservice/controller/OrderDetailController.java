package com.noux.orderservice.controller;

import com.noux.orderservice.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/order-detail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService){
        this.orderDetailService=orderDetailService;
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> findByOrderId(@PathVariable("id") Long orderId){
        return ResponseEntity.ok((orderDetailService.findAllByOrderId(orderId)));
    }
}
