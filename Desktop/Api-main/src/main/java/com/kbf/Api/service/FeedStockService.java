package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.FeedStock;

public interface FeedStockService {
	
	FeedStock createFeedStock(FeedStock feedstock);
	
	List<FeedStock> findAll();

	List<FeedStock> getFeedingStockById(int feedStockId);
		
	FeedStock updateFeedStockById(int feedStockId, FeedStock feedstock);
	
	FeedStock updateFeedStockUsage(int feedStockId, FeedStock feedstock);
	
	String deleteFeedStockById(int feedStockId);
	
	
	

}