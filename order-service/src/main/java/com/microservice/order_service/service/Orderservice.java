package com.microservice.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.order_service.dto.OrderRequest;
import com.microservice.order_service.model.Order;
import com.microservice.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class Orderservice {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        var order = mapToOrder(orderRequest);
        orderRepository.save(order);
    }

    private static Order mapToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        return order;
    }
}
