package com.kbf.Api.model;


import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sampling")
public class FishSampling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int samplingId;
	
	private String fishSpecy;
	private LocalDate sampleDate;
	private int sampleQty;
	private double totalSampleWeight;
	private double avgFishSize;
	private double targetSize;
	
	@ManyToOne
	  @JsonIgnore
	  @JoinColumn(name = "stockId")
	  private FishStock fishStock;
	
	
	
	
}
