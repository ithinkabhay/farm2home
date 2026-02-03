package com.farm2home.farmer_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String village;

    @NotBlank
    private String district;

    @NotBlank
    private String state;

    @NotBlank
    private String phone;
}
