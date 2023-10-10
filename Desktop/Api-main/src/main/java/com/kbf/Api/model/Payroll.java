package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "payroll")
public class Payroll {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payId;
	
	
	private String payType;
	private LocalDate datePaid;
	private double amount;
	private String reason; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JsonIgnore
	  @JoinColumn(name = "empId")
	  private Employee emp;
	
	@JsonIgnore
	  @OneToMany(mappedBy = "pay")
	  private List<Expense> expenses;
	
	
	
	

}
