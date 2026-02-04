package com.farm2home.grain_service.service.impl;

import com.farm2home.grain_service.dto.GrainRequest;
import com.farm2home.grain_service.dto.GrainResponse;
import com.farm2home.grain_service.entity.Grain;
import com.farm2home.grain_service.repository.GrainRepository;
import com.farm2home.grain_service.service.GrainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrainServiceImpl implements GrainService {

    private final GrainRepository repository;

    public GrainServiceImpl(GrainRepository repository) {
        this.repository = repository;
    }

    @Override
    public GrainResponse addGrain(Long farmerUserId, GrainRequest request) {

        Grain grain = Grain.builder()
                .farmerUserId(farmerUserId)
                .grainCategoryId(request.getGrainCategoryId())
                .variety(request.getVariety())
                .quantityKg(request.getQuantityKg())
                .pricePerKg(request.getPricePerKg())
                .harvestSeason(request.getHarvestSeason())
                .available(true)
                .build();

        Grain saved = repository.save(grain);

        return mapToResponse(saved);
    }

    @Override
    public List<GrainResponse> getAllAvailableGrains() {
        return repository.findByAvailableTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<GrainResponse> getMyGrains(Long farmerUserId) {
        return repository.findByFarmerUserId(farmerUserId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private GrainResponse mapToResponse(Grain g) {
        return GrainResponse.builder()
                .id(g.getId())
                .grainCategoryId(g.getGrainCategoryId())
                .variety(g.getVariety())
                .quantityKg(g.getQuantityKg())
                .pricePerKg(g.getPricePerKg())
                .harvestSeason(g.getHarvestSeason())
                .available(g.isAvailable())
                .build();
    }
}
