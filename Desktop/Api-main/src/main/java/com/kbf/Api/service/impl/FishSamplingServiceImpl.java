package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.model.FishSampling;
import com.kbf.Api.model.FishStock;
import com.kbf.Api.service.FishSamplingService;
import com.kbf.Api.repository.SamplingRepository;
import com.kbf.Api.repository.FishStockRepository;

@Service
public class FishSamplingServiceImpl implements FishSamplingService {
	
	@Autowired
	private SamplingRepository samplingRepository;
	
	@Autowired
    private FishStockRepository stockRepository;
	

	@Override
	public FishSampling createSample(int stockId, FishSampling sample) {
		
		Optional<FishStock> stock = stockRepository.findById(stockId);
    	if (stock.isPresent()) {
			FishStock originalStock = stock.get();
			sample.setFishStock(originalStock);
			sample.setAvgFishSize(Math.round(sample.getTotalSampleWeight()/sample.getSampleQty()));
		    return samplingRepository.save(sample);
    	}
		
		return null;
	}

	@Override
	public List<FishSampling> findAll() {
		
		return samplingRepository.findAll();
	}

	@Override
	public List<FishSampling> getSamplesByStockId(int stockId) {
		Optional<FishStock> stock = stockRepository.findById(stockId);
    	if (stock.isPresent()) {
			FishStock originalStock = stock.get();
		List<FishSampling> samples = originalStock.getSample().stream().collect(Collectors.toList());
		return samples;
    	}
		return null;
	}

	@Override
	public FishSampling updateSampleById(int samplingId, FishSampling sample) {
		Optional<FishSampling> sampleToUpdate = samplingRepository.findById(samplingId);
		if (sampleToUpdate.isPresent()) {
			FishSampling originalSample = sampleToUpdate.get();
            
			if (Objects.nonNull(sample.getFishSpecy()) && !"".equalsIgnoreCase(sample.getFishSpecy())) {
				originalSample.setFishSpecy(sample.getFishSpecy());
			}
			if (Objects.nonNull(sample.getSampleDate())) {
				originalSample.setSampleDate(sample.getSampleDate());
			}
			if (Objects.nonNull(sample.getSampleQty()) && sample.getSampleQty() != 0) {
				originalSample.setSampleQty(sample.getSampleQty());
			}
			
			if (Objects.nonNull(sample.getTotalSampleWeight()) && sample.getTotalSampleWeight() != 0) {
				originalSample.setTotalSampleWeight(sample.getTotalSampleWeight());
				originalSample.setAvgFishSize(Math.round(sample.getTotalSampleWeight()/sample.getSampleQty()));
			}
			
			
			//TODO : Update to use from target table
			if (Objects.nonNull(sample.getTargetSize()) && sample.getTargetSize() != 0) {
				originalSample.setTargetSize(sample.getTargetSize());
			}
			
			return samplingRepository.save(originalSample);
		}
		return null;
	}

	@Override
	public String deleteSamplingById(int samplingId) {
		if(samplingRepository.findById(samplingId).isPresent()){
			samplingRepository.deleteById(samplingId);
			return "Sample record removed successfully";
		}
		return "No such Sampling Record in database";
		
	}

}
