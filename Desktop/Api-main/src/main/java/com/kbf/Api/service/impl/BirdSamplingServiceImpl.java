package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.service.BirdSamplingService;
import com.kbf.Api.model.BirdSampling;
import com.kbf.Api.model.BirdStock;
import com.kbf.Api.repository.BirdSamplingRepository;
import com.kbf.Api.repository.BirdStockRepository;

@Service
public class BirdSamplingServiceImpl implements BirdSamplingService {

	@Autowired
	private BirdSamplingRepository samplingRepository;

	@Autowired
	BirdStockRepository flockRepo;

	@Override
	public BirdSampling createSample(int flockId, BirdSampling sample) {
		Optional<BirdStock> flock = flockRepo.findById(flockId);
		if (flock.isPresent()) {
			BirdStock originalStock = flock.get();
			sample.setFlock(originalStock);
			sample.setAvgBirdSize(Math.round(sample.getTotalSampleWeight() / sample.getSampleQty()));
			return samplingRepository.save(sample);
		}

		return null;
	}

	@Override
	public List<BirdSampling> getSamplesByFlockId(int flockId) {
		Optional<BirdStock> flock = flockRepo.findById(flockId);
		if (flock.isPresent()) {
			BirdStock originalStock = flock.get();
			List<BirdSampling> birdSamples = originalStock.getSample().stream().collect(Collectors.toList());
			return birdSamples;
		}
		return null;
	}

	@Override
	public BirdSampling updateBirdSampleById(int samplingId, BirdSampling sample) {
		Optional<BirdSampling> sampleToUpdate = samplingRepository.findById(samplingId);
		if (sampleToUpdate.isPresent()) {
			BirdSampling originalSample = sampleToUpdate.get();

			if (Objects.nonNull(sample.getBirdType()) && !"".equalsIgnoreCase(sample.getBirdType())) {
				originalSample.setBirdType(sample.getBirdType());
			}
			if (Objects.nonNull(sample.getSampleDate())) {
				originalSample.setSampleDate(sample.getSampleDate());
			}
			if (Objects.nonNull(sample.getSampleQty()) && sample.getSampleQty() != 0) {
				originalSample.setSampleQty(sample.getSampleQty());
			}

			if (Objects.nonNull(sample.getTotalSampleWeight()) && sample.getTotalSampleWeight() != 0) {
				originalSample.setTotalSampleWeight(sample.getTotalSampleWeight());
				originalSample.setAvgBirdSize(Math.round(sample.getTotalSampleWeight() / sample.getSampleQty()));
			}

			// TODO : Update to use from target table
			if (Objects.nonNull(sample.getTargetSize()) && sample.getTargetSize() != 0) {
				originalSample.setTargetSize(sample.getTargetSize());
			}

			return samplingRepository.save(originalSample);
		}
		return null;
	}

	@Override
	public String deleteBirdSamplingById(int samplingId) {
		if (samplingRepository.findById(samplingId).isPresent()) {
			samplingRepository.deleteById(samplingId);
			return "Sample record removed successfully";
		}
		return "No such Sampling Record in database";
	}

	@Override
	public List<BirdSampling> findAll() {
		// TODO Auto-generated method stub
		return samplingRepository.findAll();
	}

}
