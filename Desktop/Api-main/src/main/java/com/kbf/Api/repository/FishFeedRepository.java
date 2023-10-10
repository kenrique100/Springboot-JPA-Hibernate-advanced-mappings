package com.kbf.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kbf.Api.model.FishFeed;


public interface FishFeedRepository extends JpaRepository<FishFeed, Integer> {
	
	@Query("SELECT m FROM FishFeed m WHERE m.fishStock.stockId=:stockId")
	List<FishFeed> findBirdFeedByFlockId(Integer stock);

}
