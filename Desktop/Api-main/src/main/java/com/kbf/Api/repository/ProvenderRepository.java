package com.kbf.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kbf.Api.model.Provender;



public interface ProvenderRepository extends JpaRepository<Provender, Integer> {
	
	@Query("SELECT m FROM Provender m WHERE m.flock.flockId=:flockId")
	List<Provender> findAllByFlockId(Integer flockId);
	
	@Query("SELECT m FROM Provender m WHERE m.fishStock.stockId=:stockId")
	List<Provender> findAllByStockId(Integer stockId);

}
