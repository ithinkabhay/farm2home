package com.farm2home.grain_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3FileService {
    String uploadFile(MultipartFile file);
    String generatePresignedUrl(String key);

}
