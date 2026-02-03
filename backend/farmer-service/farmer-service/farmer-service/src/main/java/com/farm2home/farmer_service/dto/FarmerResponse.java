package com.farm2home.farmer_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FarmerResponse {

    private Long userId;
    private String name;
    private String village;
    private String district;
    private String state;
    private String phone;
}
