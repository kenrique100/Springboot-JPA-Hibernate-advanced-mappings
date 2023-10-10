package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.BirdSampling;

public interface BirdSamplingService {
	
	BirdSampling createSample(int flockId,BirdSampling sample);
	
	List<BirdSampling> findAll();

	List<BirdSampling> getSamplesByFlockId(int stockId);

	
	
	BirdSampling updateBirdSampleById(int samplingId, BirdSampling sample);
	
	String deleteBirdSamplingById(int samplingId);
	
	

}