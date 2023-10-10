package com.kbf.Api.model.dto;


import com.kbf.Api.model.Pond;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PondDTO {

	private int pondId;
	private String pondName;
	private Double area;
	private String targetFishType;	
	private int fishInStock;

	public PondDTO(Pond pond){
		this.setPondId(pond.getPondId());
		this.setPondName(pond.getPondName());
		this.setArea(pond.getArea());
		this.setTargetFishType(pond.getTargetFishType());
		this.setFishInStock(fishInStock);
	}
}
