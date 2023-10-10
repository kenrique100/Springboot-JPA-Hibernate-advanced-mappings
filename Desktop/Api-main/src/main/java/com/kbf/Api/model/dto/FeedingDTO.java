package com.kbf.Api.model.dto;

import java.time.LocalDate;

import com.kbf.Api.model.FishFeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedingDTO {

	private int feedingId;
	private LocalDate feedingDate;
	private int feedOut;
	private int qtyFed;
	private double avgFishSize;
	private int pelletSize;
	private int timesFed;
	
	public FeedingDTO (FishFeed feeding){
		this.setFeedingId(feeding.getFeedingId());
		this.setFeedingDate(feeding.getFeedingDate());
		//this.setFeedOut(feeding.getFeedOut());
		this.setQtyFed(feeding.getQtyFed());
		this.setAvgFishSize(feeding.getAvgFishSize());
		this.setPelletSize(feeding.getPelletSize());
		this.setTimesFed(feeding.getTimesFed());
	}
	
	
	
	
	

}
