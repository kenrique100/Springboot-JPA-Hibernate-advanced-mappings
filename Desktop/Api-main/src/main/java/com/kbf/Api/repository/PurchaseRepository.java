package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.Purchase;




public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}
