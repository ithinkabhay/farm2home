package com.farm2home.grain_service.service.impl;

import com.farm2home.grain_service.service.S3FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;
@Service
public class S3FileServiceImpl implements S3FileService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;
    public S3FileServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(MultipartFile file) {

        String key = "grains/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try {
            s3Client.putObject(
                    request,
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes())
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3");
        }

        return "https://" + bucket + ".s3.amazonaws.com/" + key;
    }
}
