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

    public GrainMediaController(S3FileService s3FileService, GrainRepository grainRepository) {
        this.s3FileService = s3FileService;
        this.grainRepository = grainRepository;
    }

    @PostMapping("/{grainId}/upload")
    public ResponseEntity<?> uploadMedia(
            @PathVariable Long grainId,
            @RequestPart("file") MultipartFile file,
            @RequestParam(defaultValue = "IMAGE") String type    ) {

        var grain = grainRepository.findById(grainId)
                .orElseThrow(() -> new RuntimeException("Grain not found"));

        String url = s3FileService.uploadFile(file);

        if ("IMAGE".equalsIgnoreCase(type)) {
            grain.setImageUrl(url);
        } else if ("VIDEO".equalsIgnoreCase(type)) {
            grain.setVideoUrl(url);
        }

        grainRepository.save(grain);

        return ResponseEntity.ok(url);
    }
}
