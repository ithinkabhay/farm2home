package com.farm2home.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateOrderRequest {
    @NotNull
    private Long grainId;

    @NotNull
    private Long farmerUserId;

    @Positive
    private Double quantityKg;

    @Positive
    private Double pricePerKg;
}
