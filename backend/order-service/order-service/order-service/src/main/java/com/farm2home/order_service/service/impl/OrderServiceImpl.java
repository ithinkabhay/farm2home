package com.farm2home.order_service.service.impl;

import com.farm2home.order_service.dto.CreateOrderRequest;
import com.farm2home.order_service.dto.OrderCreatedEvent;
import com.farm2home.order_service.dto.OrderResponse;
import com.farm2home.order_service.entity.Order;
import com.farm2home.order_service.entity.OrderStatus;
import com.farm2home.order_service.repository.OrderRepository;
import com.farm2home.order_service.service.OrderEventPublisher;
import com.farm2home.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public OrderServiceImpl(OrderRepository repository, OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public OrderResponse createOrder(Long customerUserId, CreateOrderRequest request) {

        Order order = Order.builder()
                .customerUserId(customerUserId)
                .farmerUserId(request.getFarmerUserId())
                .grainId(request.getGrainId())
                .quantityKg(request.getQuantityKg())
                .pricePerKg(request.getPricePerKg())
                .totalAmount(request.getQuantityKg() * request.getPricePerKg())
                .status(OrderStatus.CREATED)
                .build();

        Order saved = repository.save(order);

        eventPublisher.publishOrderCreated(
                OrderCreatedEvent.builder()
                        .orderId(saved.getId())
                        .customerUserId(saved.getCustomerUserId())
                        .farmerUserId(saved.getFarmerUserId())
                        .totalAmount(saved.getTotalAmount())
                        .build()
        );

        return mapToResponse(saved);
    }

    @Override
    public List<OrderResponse> getOrdersForFarmer(Long farmerUserId) {
        return repository.findByFarmerUserId(farmerUserId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OrderResponse acceptOrder(Long orderId, Long farmerUserId) {

        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getFarmerUserId().equals(farmerUserId)) {
            throw new RuntimeException("Unauthorized action");
        }

        order.setStatus(OrderStatus.ACCEPTED_BY_FARMER);
        repository.save(order);

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .grainId(order.getGrainId())
                .quantityKg(order.getQuantityKg())
                .pricePerKg(order.getPricePerKg())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
