package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.FishStock;



public interface FishStockRepository extends JpaRepository<FishStock, Integer> {

}
