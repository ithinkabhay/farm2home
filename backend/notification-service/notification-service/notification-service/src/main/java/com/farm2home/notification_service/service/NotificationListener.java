package com.farm2home.notification_service.service;

import com.farm2home.notification_service.dto.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "order-created", groupId = "notification-group")
    public void handleOrderCreated(OrderCreatedEvent event) {

        System.out.println("📩 New Order Event Received!");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Farmer ID: " + event.getFarmerUserId());
        System.out.println("Total Amount: " + event.getTotalAmount());
    }
}
