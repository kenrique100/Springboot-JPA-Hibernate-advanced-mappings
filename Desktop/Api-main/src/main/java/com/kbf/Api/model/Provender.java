package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "provender")
public class Provender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batchId;
	
	private String type;
	private LocalDate batchDate;
	private double qtyPrepared;
	// get from feeding after computing the total 
	private double qtyUsed;	
	private double cost;
	// this should be a transient value 
	private double qtyRemaining;
	// based on pearson table formulation
	// should add a list or link to formula table
	private int totalcpContent;
	private int totalEnergyContent;
	private String formulaName;
	private boolean isUsed;
	
	
	@JsonIgnore
	  @OneToMany(mappedBy = "provender")
	  private List<FeedStock> feedStock;
	
	
	@JsonIgnore
	  @OneToMany(mappedBy = "provender")
	  private List<BirdFeed> birdfeed;
	
	@JsonIgnore
	  @OneToMany(mappedBy = "provender")
	  private List<FishFeed> feeding;
	
	@ManyToOne
	@JsonIgnore
	  @JoinColumn(name = "flockId")
	  private BirdStock flock;
	
	@ManyToOne
	@JsonIgnore
	  @JoinColumn(name = "stockId")
	  private FishStock fishStock;
	
	

}
