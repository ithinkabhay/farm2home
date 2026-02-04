package com.farm2home.grain_service.service;

import com.farm2home.grain_service.dto.GrainRequest;
import com.farm2home.grain_service.dto.GrainResponse;

import java.util.List;

public interface GrainService {

    GrainResponse addGrain(Long farmerUserId, GrainRequest request);

    List<GrainResponse> getAllAvailableGrains();

    List<GrainResponse> getMyGrains(Long farmerUserId);
}
