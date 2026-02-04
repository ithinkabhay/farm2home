package com.farm2home.grain_service.repository;

import com.farm2home.grain_service.entity.GrainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrainCategoryRepository extends JpaRepository<GrainCategory, Long> {
    Optional<GrainCategory> findByCode(String code);

    List<GrainCategory> findByActiveTrue();
}
