package com.farm2home.farmer_service.controller;

import com.farm2home.farmer_service.dto.FarmerRequest;
import com.farm2home.farmer_service.dto.FarmerResponse;
import com.farm2home.farmer_service.service.FarmerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/profile")
    public ResponseEntity<FarmerResponse> createOrUpdateProfile(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-USER-ROLE") String role,
            @Valid @RequestBody FarmerRequest request
    ) {

        if (!"FARMER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        FarmerResponse response = farmerService.createOrUpdateProfile(userId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<FarmerResponse> getProfile(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-USER-ROLE") String role
    ) {

        if (!"FARMER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        FarmerResponse response = farmerService.getProfile(userId);
        return ResponseEntity.ok(response);
    }
}
