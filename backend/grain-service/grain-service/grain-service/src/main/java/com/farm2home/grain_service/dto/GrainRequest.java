package com.farm2home.grain_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrainRequest {

    @NotNull
    private Long grainCategoryId;

    private String variety;

    @Positive
    private Double quantityKg;

    @Positive
    private Double pricePerKg;

    private String harvestSeason;
}
