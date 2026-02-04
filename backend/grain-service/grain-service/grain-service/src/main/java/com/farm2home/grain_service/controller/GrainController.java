package com.farm2home.grain_service.controller;

import com.farm2home.grain_service.dto.GrainRequest;
import com.farm2home.grain_service.dto.GrainResponse;
import com.farm2home.grain_service.service.GrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grains")
public class GrainController {

    private final GrainService grainService;

    public GrainController(GrainService grainService) {
        this.grainService = grainService;
    }

    // Farmer adds grain
    @PostMapping
    public ResponseEntity<GrainResponse> addGrain(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-USER-ROLE") String role,
            @Valid @RequestBody GrainRequest request
    ) {
        if (!"FARMER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(grainService.addGrain(userId, request));
    }

    // Public listing
    @GetMapping
    public List<GrainResponse> getAllGrains() {
        return grainService.getAllAvailableGrains();
    }

    // Farmer's own grains
    @GetMapping("/my")
    public ResponseEntity<List<GrainResponse>> getMyGrains(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-USER-ROLE") String role
    ) {
        if (!"FARMER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(grainService.getMyGrains(userId));
    }
}
