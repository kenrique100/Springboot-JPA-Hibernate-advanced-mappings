package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.Provender;

public interface ProvenderService {
	
	Provender createProvender(Provender mixedFeed);
	
	List<Provender> findAll();

	List<Provender> getProvenderByBirdFlockId(int flockId);
	
	List<Provender> getProvenderByFishStockId(int stockId);

	Provender createProvenderByBirdFlockId(int BirdFlockId,Provender provender);
	
	Provender createProvenderByFishStockId(int fishStockId,Provender provender);
	
	Provender updateProvender(Provender mixedFeed);
	
	String deleteProvenderById(int mixedFeedId);
	
	

}