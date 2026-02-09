package com.farm2home.grain_service.controller;

import com.farm2home.grain_service.repository.GrainRepository;
import com.farm2home.grain_service.service.S3FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/grains/media")
public class GrainMediaController {

    private final S3FileService s3FileService;
    private final GrainRepository grainRepository;

    public GrainMediaController(S3FileService s3FileService,
                                GrainRepository grainRepository) {
        this.s3FileService = s3FileService;
        this.grainRepository = grainRepository;
    }

    @PostMapping(
            value = "/{grainId}/upload",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<?> uploadMedia(
            @PathVariable Long grainId,
            @RequestPart("file") MultipartFile file,
            @RequestParam(defaultValue = "IMAGE") String type
    ) {

        var grain = grainRepository.findById(grainId)
                .orElseThrow(() -> new RuntimeException("Grain not found"));

        // upload to S3 → returns ONLY KEY
        String key = s3FileService.uploadFile(file);

        if ("IMAGE".equalsIgnoreCase(type)) {
            grain.setImageKey(key);
        } else if ("VIDEO".equalsIgnoreCase(type)) {
            grain.setVideoKey(key);
        } else {
            return ResponseEntity.badRequest().body("Invalid media type");
        }

        grainRepository.save(grain);

        return ResponseEntity.ok("Media uploaded successfully");
    }
}
