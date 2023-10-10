package com.kbf.Api.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "birdsampling")
public class BirdSampling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int samplingId;
	
	private String birdType;
	private LocalDate sampleDate;
	private int sampleQty;
	private double totalSampleWeight;
	private double avgBirdSize;
	private double targetSize;
	
	@ManyToOne
	  @JoinColumn(name = "flockId")
	  private BirdStock flock;
	
	
	
	
}
