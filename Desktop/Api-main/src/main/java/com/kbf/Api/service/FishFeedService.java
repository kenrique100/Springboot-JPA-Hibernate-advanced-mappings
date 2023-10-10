package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.FishFeed;
import com.kbf.Api.model.dto.FeedingDTO;

public interface FishFeedService {
	
	FishFeed createFeeding(int stockId,FishFeed feeding);
	
	List<FishFeed> findAll();

	List<FishFeed> getFeedingStockById(int stockId);

	List<FeedingDTO> getFeedingDTOStockById(int stockId);
	
	String deleteFeedingById(int feedingId);
	
    List<FishFeed> getFishFeedingByStockId(int stockId);	
	
    FishFeed updateFeedingById(int feedingId, FishFeed feeding) throws Exception;
	
	

}