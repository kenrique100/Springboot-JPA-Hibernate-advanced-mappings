package com.kbf.Api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "expense")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expenseId;

	private String type;
	private String reason;
	private double expenseAmount;
	private LocalDate expenseDate;
	@Column(columnDefinition = "integer default 1")
	private int qtyPurchase;
	@Column(columnDefinition = "integer default 0")
	private double amountPaid;
	@Column(columnDefinition = "integer default 0")
	private double dueBalance;
	private String status;
	@Lob
	@Column
	private byte[] receipt;
	private String paidTo;
	private String paidBy;
	private String fundSource;
	private String enteredBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "productId")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "payId")
	private Payroll pay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "miscId")
	private Miscellaneous misc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "feedStockId")
	private FeedStock feedStock;
	@ManyToOne(fetch = FetchType.LAZY)

	@JsonIgnore
	@JoinColumn(name = "suppliesId")
	private Supplies supplies;

}
