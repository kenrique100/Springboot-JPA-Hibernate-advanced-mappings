package com.kbf.Api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.Order;


public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByOrderByCreatedAtDesc();

    List<Order> findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(String id, String description);
}
