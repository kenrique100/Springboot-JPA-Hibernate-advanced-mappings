package com.kbf.Api.model;


import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "health")
public class Health {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int healthId;
	
	private String subject;
	private LocalDate treatmentdate;
	private String disease;
	private String treatmentType;
	private String treatmentName;
	private int totalSujectTreated;
	private String treatmentBy;
	
	@ManyToOne
	  @JsonIgnore
	  @JoinColumn(name = "stockID")
	  private FishStock stock;
	
	@ManyToOne
	  @JsonIgnore
	  @JoinColumn(name = "flockId")
	  private BirdStock flock;
	
	
	
	
}
