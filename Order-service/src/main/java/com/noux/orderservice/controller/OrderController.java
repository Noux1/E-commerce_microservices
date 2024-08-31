package com.noux.orderservice.controller;

import com.noux.orderservice.exception.OrderNotFoundException;
import com.noux.orderservice.request.Order.DTO.OrderRequest;
import com.noux.orderservice.response.Order.DTO.OrderErrorResponse;
import com.noux.orderservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){

        this.orderService= orderService;

    }

    @PostMapping()
    public ResponseEntity<Object> createOrder( @RequestBody @Valid OrderRequest request){
        try{
        return ResponseEntity.ok(orderService.createOrder(request));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new OrderErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OrderErrorResponse("Internal server error", e.getMessage()));
        }

    }

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(orderService.findOrderById(id));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new OrderErrorResponse("Order not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new OrderErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OrderErrorResponse("Internal server error", e.getMessage()));
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @RequestBody @Valid OrderRequest orderRequest) {
//        try {
//            return ResponseEntity.ok(orderService.updateOrder(id, orderRequest));
//        } catch (OrderNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new OrderErrorResponse("Order not found", e.getMessage()));
//        } catch (ValidationException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new OrderErrorResponse("Validation failed", e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new OrderErrorResponse("Internal server error", e.getMessage()));
//        }
//    }






}

