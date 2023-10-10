package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
@Table(name = "feedstock")
public class FeedStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedstockId;
	
	private String name;
	private LocalDate stockDate;
	private double unitPrice;	
	private double qtyBought;
	private double inStockQty;
	@Column(columnDefinition = "integer default 0")	
	private double projectedStockQty;
	@Column(columnDefinition = "integer default 0")
	private double qtyUsed;
	private double stockRemaining;
	private int cpContent;
	private int energyContent;
	private boolean isLow;
	private boolean isFinished;	
	private double broilerFeedUsed;
	private double fishFeedUsed;
	private double duckAndFowlFeedUsed;
	private double pigFeedUsed;
	private double rabbitFeedUsed;
	private double otherFeedUsed;
	
	
	
	
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JsonIgnore
	  @JoinColumn(name = "batchId")
	  private Provender provender;
	
	@JsonIgnore
	  @OneToMany(mappedBy = "feedStock")
	  private List<Expense> expenses;
	
	
	
	
	
	

}
