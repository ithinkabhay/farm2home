package com.farm2home.grain_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GrainResponse {

    private Long id;
    private Long grainCategoryId;
    private String variety;
    private Double quantityKg;
    private Double pricePerKg;
    private String harvestSeason;
    private boolean available;
}
