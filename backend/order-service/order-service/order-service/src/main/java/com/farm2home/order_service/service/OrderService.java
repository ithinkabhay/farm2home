package com.farm2home.order_service.service;

import com.farm2home.order_service.dto.CreateOrderRequest;
import com.farm2home.order_service.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(Long customerUserId, CreateOrderRequest request);

    List<OrderResponse> getOrdersForFarmer(Long farmerUserId);

    OrderResponse acceptOrder(Long orderId, Long farmerUserId);
}
