package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "supplies")
public class Supplies {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplyId;
	
	private String name;
	private LocalDate datePurchase;
	private String purchasedFrom;
	@Lob
	@Column
	private byte[] receipt;
	private double amountPaid;
	private String reason; 
	
	
		
	
	@JsonIgnore
	  @OneToMany(mappedBy = "supplies")
	  private List<Expense> expenses;
	
	

}
