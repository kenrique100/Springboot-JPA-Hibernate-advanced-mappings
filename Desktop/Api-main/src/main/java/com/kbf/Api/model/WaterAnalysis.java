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
@Table(name = "H2OAnalysis")
public class WaterAnalysis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int analysisId;
	
	private String fishSpecy;
	private LocalDate analysisDate;
	private int sampleQty;
	private double oxygenLevel;
	private double temp;
	private double ph;
	private double nitrate;
	private double hardness;
	private double alkalinity;
	
	@ManyToOne
	  @JsonIgnore
	  @JoinColumn(name = "pondId")
	  private Pond pond;
	
	
	
	
}
