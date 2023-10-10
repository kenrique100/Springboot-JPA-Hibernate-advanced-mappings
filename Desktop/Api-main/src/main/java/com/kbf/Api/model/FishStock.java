package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "fishstock")
public class FishStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  stockId;

	private String batch;
	private String fishSpecy;
	private LocalDate stockDate;
	@NotNull(message = "totalStock is mandatory")
	private int totalStock;
	private int nbrOfDays;
	@NotBlank(message = "Purpose is mandatory")
	private String purpose;
	private int reduction;
	private int mortality;
	private int stockRemaining;
	private boolean isSoldOut;
	private boolean isArchive;	
	private int fishPondId;	
	private String fishPondName;	
	
	 @JsonIgnore
	  @OneToMany(mappedBy = "fishStock",fetch = FetchType.LAZY)
	  private List<FishSampling> sample;
	  
	  @JsonIgnore
	  @OneToMany(mappedBy = "fishStock",fetch = FetchType.LAZY)
	  private List<FishFeed> feed;
	  
	  @JsonIgnore
	  @OneToMany(mappedBy = "fishStock",fetch = FetchType.LAZY)
	  private List<Provender> provender;
	  
//	  @ManyToOne(fetch = FetchType.LAZY)
//	  @JsonIgnore
//	  @JoinColumn(name = "pondId")
//	  private Pond pond;
//	  
	

}
