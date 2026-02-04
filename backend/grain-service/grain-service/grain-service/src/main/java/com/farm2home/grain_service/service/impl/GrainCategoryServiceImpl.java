package com.farm2home.grain_service.service.impl;

import com.farm2home.grain_service.dto.GrainCategoryResponse;
import com.farm2home.grain_service.repository.GrainCategoryRepository;
import com.farm2home.grain_service.service.GrainCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrainCategoryServiceImpl implements GrainCategoryService {

    private final GrainCategoryRepository repository;

    public GrainCategoryServiceImpl(GrainCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GrainCategoryResponse> getActiveCategories() {
        return repository.findByActiveTrue()
                .stream()
                .map(c -> GrainCategoryResponse.builder()
                        .id(c.getId())
                        .code(c.getCode())
                        .nameEn(c.getNameEn())
                        .nameHi(c.getNameHi())
                        .build())
                .toList();
    }
}
