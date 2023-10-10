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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "income")
public class Sales {
	//rename table to sales
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int incomeId;
	
	private String type;
	private LocalDate dateSold;
	@Column(columnDefinition = "integer default 1")	
	private int qtySold;
	private String reference;
	private String status;
	private double amountSold;
	private double amountReceived;
	private double dueBalance;
	private String enteredBy;
	private String origin;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JsonIgnore
	  @JoinColumn(name = "productId")
	  private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JsonIgnore
	  @JoinColumn(name = "clientId")
	  private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JsonIgnore
	  @JoinColumn(name = "investmentId")
	  private Investment invest;
	
	

}
