package com.farm2home.order_service.entity;


public enum OrderStatus {
    CREATED,            // customer placed order
    ACCEPTED_BY_FARMER, // farmer accepted
    REJECTED_BY_FARMER, // farmer rejected
    CANCELLED           // customer cancelled
}
