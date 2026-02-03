package com.farm2home.farmer_service.service;

import com.farm2home.farmer_service.dto.FarmerRequest;
import com.farm2home.farmer_service.dto.FarmerResponse;

public interface FarmerService {

    FarmerResponse createOrUpdateProfile(Long userId, FarmerRequest request);

    FarmerResponse getProfile(Long userId);
}
