package com.farm2home.grain_service.service;

import com.farm2home.grain_service.dto.GrainCategoryResponse;

import java.util.List;

public interface GrainCategoryService {

    List<GrainCategoryResponse> getActiveCategories();
}
