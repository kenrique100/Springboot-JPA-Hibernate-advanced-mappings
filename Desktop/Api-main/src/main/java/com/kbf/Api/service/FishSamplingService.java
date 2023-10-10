package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.FishSampling;

public interface FishSamplingService {
	
	FishSampling createSample(int stockId,FishSampling sample);
	
	List<FishSampling> findAll();

	List<FishSampling> getSamplesByStockId(int stockId);

	
	
	FishSampling updateSampleById(int samplingId, FishSampling sample);
	
	String deleteSamplingById(int samplingId);
	
	

}