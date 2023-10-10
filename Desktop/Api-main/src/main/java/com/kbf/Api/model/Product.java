package com.kbf.Api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String name;
	private String code;
	private double qtyInStock;
	private double unitPrice;
	private String unitOfMeasure;
	private double cost;
	private String category;
	private String notes;
	
	
//	 @OneToOne	 
//	  @JoinColumn(name = "stockID")
//	  private FishStockOld stock;
	 
	 @OneToOne	 
	  @JoinColumn(name = "flockID")
	  private BirdStock flock;
	
	//make it sales
	@JsonIgnore
	  @OneToMany(mappedBy = "product")
	  private List<Sales> income;
	
	// supplies???
	@JsonIgnore
	  @OneToMany(mappedBy = "product")
	  private List<Expense> expenses;
	
	

}
