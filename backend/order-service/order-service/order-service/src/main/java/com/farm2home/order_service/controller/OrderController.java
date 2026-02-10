package com.farm2home.order_service.controller;

import com.farm2home.order_service.dto.CreateOrderRequest;
import com.farm2home.order_service.dto.OrderResponse;
import com.farm2home.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // CUSTOMER places order
    @PostMapping
    public OrderResponse createOrder(
            @RequestHeader("X-USER-ID") Long customerUserId,
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return orderService.createOrder(customerUserId, request);
    }

    // FARMER views orders
    @GetMapping("/farmer")
    public List<OrderResponse> getFarmerOrders(
            @RequestHeader("X-USER-ID") Long farmerUserId
    ) {
        return orderService.getOrdersForFarmer(farmerUserId);
    }

    // FARMER accepts order
    @PatchMapping("/{orderId}/accept")
    public OrderResponse acceptOrder(
            @PathVariable Long orderId,
            @RequestHeader("X-USER-ID") Long farmerUserId
    ) {
        return orderService.acceptOrder(orderId, farmerUserId);
    }
}
