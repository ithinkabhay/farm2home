package com.farm2home.notification_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerUserId;
    private Long farmerUserId;
    private Double totalAmount;
}
