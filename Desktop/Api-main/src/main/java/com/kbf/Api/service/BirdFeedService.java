package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.BirdFeed;

public interface BirdFeedService {
	
	BirdFeed createFeeding(int flockId,BirdFeed feeding);
	
	List<BirdFeed> findAll();

	List<BirdFeed> getBirdFeedingByFlockId(int flockId);	
	
	BirdFeed updateFeedingById(int feedingId, BirdFeed feeding) throws Exception;
	
	String deleteBirdFeedingById(int feedingId);
	
	

}