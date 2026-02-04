package com.farm2home.grain_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grain_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // stable code (WHEAT, RICE, JOWAR, etc.)
    @Column(nullable = false, unique = true)
    private String code;

    // English display name
    @Column(nullable = false)
    private String nameEn;

    // Hindi display name
    @Column(nullable = false)
    private String nameHi;

    private boolean active = true;
}

