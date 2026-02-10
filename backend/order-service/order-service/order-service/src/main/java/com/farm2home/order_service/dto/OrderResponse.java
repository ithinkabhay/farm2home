package com.farm2home.order_service.dto;

import com.farm2home.order_service.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long orderId;
    private Long grainId;
    private Double quantityKg;
    private Double pricePerKg;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
