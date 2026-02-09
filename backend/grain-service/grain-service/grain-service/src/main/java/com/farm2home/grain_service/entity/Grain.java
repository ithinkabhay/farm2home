package com.farm2home.grain_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "grains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Owner (from X-USER-ID header)
    @Column(nullable = false)
    private Long farmerUserId;

    // Grain category reference (NO FK constraint across services)
    @Column(nullable = false)
    private Long grainCategoryId;

    private String variety;

    private Double quantityKg;

    private Double pricePerKg;

    private String harvestSeason;

    private boolean available = true;

    private LocalDateTime createdAt;

    private String imageKey;

    private String videoKey;



    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

