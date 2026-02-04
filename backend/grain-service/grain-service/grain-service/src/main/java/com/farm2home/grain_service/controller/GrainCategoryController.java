package com.farm2home.grain_service.controller;

import com.farm2home.grain_service.dto.GrainCategoryResponse;
import com.farm2home.grain_service.service.GrainCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grain-categories")
public class GrainCategoryController {

    private final GrainCategoryService service;

    public GrainCategoryController(GrainCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<GrainCategoryResponse> getCategories() {
        return service.getActiveCategories();
    }
}
