package com.farm2home.order_service.repository;

import com.farm2home.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByFarmerUserId(Long farmerUserId);

    List<Order> findByCustomerUserId(Long customerUserId);

}
