package com.noux.orderservice.service;

import com.noux.orderservice.entity.Order;
import com.noux.orderservice.event.OrderConfirmation;
import com.noux.orderservice.event.OrderProducer;
import com.noux.orderservice.exception.OrderNotFoundException;
import com.noux.orderservice.proxy.Client.ClientProxy;
import com.noux.orderservice.proxy.Product.ProductProxy;
import com.noux.orderservice.proxy.Product.PurchaseRequest;
import com.noux.orderservice.repository.OrderRepository;
import com.noux.orderservice.request.Order.DTO.OrderRequest;
import com.noux.orderservice.request.Order.mapper.OrderRequestMapper;
import com.noux.orderservice.request.OrderDetail.DTO.OrderDetailRequest;
import com.noux.orderservice.response.Order.DTO.OrderResponse;
import com.noux.orderservice.response.Order.mapper.OrderResponseMapper;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderRequestMapper orderRequestMapper;

    private final OrderResponseMapper orderResponseMapper;

    private final ClientProxy client;

    private final ProductProxy productProxy;

    private final OrderDetailService orderDetailService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final OrderProducer orderProducer;

    @Autowired
    public OrderService (OrderRepository orderRepository , OrderRequestMapper orderRequestMapper ,
                         OrderResponseMapper orderResponseMapper, ClientProxy client ,
                         ProductProxy productProxy , OrderDetailService orderDetailService ,
                         KafkaTemplate<String, Object> kafkaTemplate,
                         OrderProducer orderProducer
                         ){
        this.orderRepository= orderRepository;
        this.orderRequestMapper= orderRequestMapper;
        this.orderResponseMapper= orderResponseMapper;
        this.client= client;
        this.productProxy= productProxy;
        this.orderDetailService = orderDetailService;
        this.kafkaTemplate = kafkaTemplate;
        this.orderProducer=orderProducer;
    }

    public OrderResponse createOrder(OrderRequest orderRequest){

        var client = this.client.findClientById(orderRequest.getClientId())
                .orElseThrow(() -> new NotFoundException("Client Not Found with Id " + orderRequest.getClientId()));

         var purchaseProducts =  productProxy.purchaseProducts(orderRequest.getProducts());

        var orderEntity = orderRequestMapper.requestMapping(orderRequest);

        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setLastModifiedDate(LocalDateTime.now());
        var savedOrder = orderRepository.save(orderEntity);
        for (PurchaseRequest purchaseRequest: orderRequest.getProducts()){
            OrderDetailRequest orderDetailRequest = OrderDetailRequest.builder()
                    .OrderId(savedOrder.getId())
                    .qte(purchaseRequest.getQte())
                    .productId(purchaseRequest.getProductId())
                    .build();

            orderDetailService.saveOrderDetail(orderDetailRequest);
        }
//        OrderConfirmation orderConfirmation = OrderConfirmation.builder()
//                .orderReference(savedOrder.getReference())
//                .totalAmount(savedOrder.getTotalAmount())
//                .paymentMethod(savedOrder.getPaymentMethod())
//                .client(client)
//                .products(purchaseProducts)
//                .build();
//        MessageBuilder<OrderConfirmation> messageBuilder = MessageBuilder
//                .withPayload(orderConfirmation)
//                .setHeader(TOPIC, "order-topic");
//        kafkaTemplate.send(messageBuilder.build());

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.getReference(),
                        orderRequest.getTotalAmount(),
                        orderRequest.getPaymentMethod(),
                        client,
                        purchaseProducts
                )
        );


        return orderResponseMapper.responseMapping(savedOrder);
    }


    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
              return   orders.stream()
                .map(orderResponseMapper::responseMapping)
                .collect(Collectors.toList());

    }

    public OrderResponse findOrderById(Long id){
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Cannot find order :: No order found with id : %s " ,id)
                ));
        return orderResponseMapper.responseMapping(order);

    }

//    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
//        var existingOrder = orderRepository.findById(id)
//                .orElseThrow(() -> new OrderNotFoundException(
//                        String.format("Cannot find order :: No order found with id : %s", id)
//                ));
//
//        if (orderRequest.getReference() != null) {
//            existingOrder.setReference(orderRequest.getReference());
//        }
//        if (orderRequest.getTotalAmount() != null) {
//            existingOrder.setTotalAmount(orderRequest.getTotalAmount());
//        }
//        if (orderRequest.getPaymentMethod() != null) {
//            existingOrder.setPaymentMethod(orderRequest.getPaymentMethod());
//        }
//        if (orderRequest.getClientId() != null) {
//            existingOrder.setClientId(orderRequest.getClientId());
//        }
//
//        existingOrder.setLastModifiedDate(LocalDateTime.now());
//        var updatedOrder = orderRepository.save(existingOrder);
//
//        OrderUpdateEvent orderUpdateEvent = OrderUpdateEvent.builder()
//                .orderId(updatedOrder.getId())
//                .reference(updatedOrder.getReference())
//                .totalAmount(updatedOrder.getTotalAmount())
//                .paymentMethod(updatedOrder.getPaymentMethod())
//                .clientId(updatedOrder.getClientId())
//                .build();
//        kafkaTemplate.send("orderUpdateTopic", orderUpdateEvent);
//
//        return orderResponseMapper.responseMapping(updatedOrder);
//    }





}
