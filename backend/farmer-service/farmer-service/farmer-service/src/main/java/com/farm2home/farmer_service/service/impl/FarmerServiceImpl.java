package com.farm2home.farmer_service.service.impl;

import com.farm2home.farmer_service.dto.FarmerRequest;
import com.farm2home.farmer_service.dto.FarmerResponse;
import com.farm2home.farmer_service.entity.Farmer;
import com.farm2home.farmer_service.repository.FarmerRepository;
import com.farm2home.farmer_service.service.FarmerService;
import org.springframework.stereotype.Service;

@Service
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public FarmerResponse createOrUpdateProfile(Long userId, FarmerRequest request) {

        Farmer farmer = farmerRepository.findByUserId(userId)
                .orElse(Farmer.builder().userId(userId).build());

        farmer.setName(request.getName());
        farmer.setVillage(request.getVillage());
        farmer.setDistrict(request.getDistrict());
        farmer.setState(request.getState());
        farmer.setPhone(request.getPhone());

        Farmer saved = farmerRepository.save(farmer);

        return FarmerResponse.builder()
                .userId(saved.getUserId())
                .name(saved.getName())
                .village(saved.getVillage())
                .district(saved.getDistrict())
                .state(saved.getState())
                .phone(saved.getPhone())
                .build();
    }

    @Override
    public FarmerResponse getProfile(Long userId) {

        Farmer farmer = farmerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Farmer profile not found"));

        return FarmerResponse.builder()
                .userId(farmer.getUserId())
                .name(farmer.getName())
                .village(farmer.getVillage())
                .district(farmer.getDistrict())
                .state(farmer.getState())
                .phone(farmer.getPhone())
                .build();
    }
}
