package com.kbf.Api.model.dto;


import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TransactionReportDTO {
	
	private String type;
	private double amount;
	private LocalDate date;
	private int quantity;
	private String reference;
	private String productName;
	private String enteredBy;
	private String origin;
	
	 
	
	
}
