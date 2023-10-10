package com.kbf.Api.model.dto;


import java.time.LocalDate;

import com.kbf.Api.model.FishSampling;

import lombok.Data;


@Data
public class SamplingDTO {
	private int samplingId;
	private String fishSpecy;
	private LocalDate sampleDate;
	private double avgFishSize;
	private double targetSize;
	
	public SamplingDTO(FishSampling sample){
		this.setSamplingId(sample.getSamplingId());
		this.setFishSpecy(sample.getFishSpecy());
		this.setSampleDate(sample.getSampleDate());
		this.setAvgFishSize(Math.round(sample.getTotalSampleWeight()/sample.getSampleQty()));
		this.setTargetSize(sample.getTargetSize()); //should come from growth table
	}
	
	
	
	
}
