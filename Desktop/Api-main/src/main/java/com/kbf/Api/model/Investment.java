package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "investment")
public class Investment {
	//rename table to sales
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int investmentId;
	
	private String type;
	private LocalDate date;
	private String reference;
	private String status;
	private double amountDisbursed;
	@Column(columnDefinition = "integer default 0")
	private double balance;
	private String origin;
	private String destination;
	private String enteredBy;
	
	//make it sales
	@JsonIgnore
	 @OneToMany(mappedBy = "invest")
	  private List<Sales> income;
		
	
	
	
	

}
