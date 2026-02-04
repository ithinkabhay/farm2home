package com.farm2home.grain_service.repository;

import com.farm2home.grain_service.entity.Grain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrainRepository extends JpaRepository<Grain, Long> {

    List<Grain> findByAvailableTrue();

    List<Grain> findByFarmerUserId(Long farmerUserId);
}
