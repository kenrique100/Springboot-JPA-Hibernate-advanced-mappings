package com.kbf.Api.model;

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
@Table(name = "supplier")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	
	private String businessName;
	private String contactName;
	private String referenceID;
	private String email;
	private String telNumber;
	private String status;
	
	
	
	
	
	  @JsonIgnore
	  @OneToMany(mappedBy = "supplier")
	  private List<Purchase> purchases;
	
	
	
	
	
	

}
