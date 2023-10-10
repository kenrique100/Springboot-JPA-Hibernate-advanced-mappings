package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "miscellaneous")
public class Miscellaneous {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int miscId;
	
	private String name;
	private LocalDate dateDisbursed;
	private double amount;
	private String reason; 
	
	
		
	
	@JsonIgnore
	  @OneToMany(mappedBy = "misc")
	  private List<Expense> expenses;
	
	

}
