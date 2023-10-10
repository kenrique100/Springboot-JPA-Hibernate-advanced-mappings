package com.kbf.Api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;
	
	private String type;
	private LocalDate purchaseDate;
	
	@Column(columnDefinition = "integer default 1")	
	private int quantity;
	private double total;
	private double paidAmount;
	@Column(columnDefinition = "integer default 0")	
	private double dueBalance;
	private String reference;
	private String reason; 
	
	
		
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JsonIgnore
	  @JoinColumn(name = "supplierId")
	  private Supplier supplier;
	
	



}
