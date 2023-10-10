package com.kbf.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kbf.Api.model.BirdFeed;

public interface BirdFeedRepository extends JpaRepository<BirdFeed, Integer> {
	
	@Query("SELECT m FROM BirdFeed m WHERE m.flock.flockId=:flockId")
	List<BirdFeed> findBirdFeedByFlockId(Integer flockId);
	
	boolean findByProvenderId(Integer provenderId);

}
