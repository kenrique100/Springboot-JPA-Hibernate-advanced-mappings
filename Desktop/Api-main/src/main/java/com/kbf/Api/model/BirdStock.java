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
import jakarta.persistence.OneToOne;
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

@Table(name = "flock")
public class BirdStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flockId;

	private String batch;
	private String flockType;
	private LocalDate stockDate;
	@NotNull(message = "nbrOfBirds is mandatory")
	private int nbrOfBirds;

	private int nbrOfDays;
	@NotBlank(message = "Purpose is mandatory")
	private String purpose;
	private int reduction;
	private int mortality;
	private int stockRemaining;
	private boolean isSoldOut;
	private boolean isArchive;

	@JsonIgnore
	@OneToMany(mappedBy = "flock", fetch = FetchType.LAZY)
	private List<BirdFeed> birdFeed;

	@JsonIgnore
	@OneToMany(mappedBy = "flock", fetch = FetchType.LAZY)
	private List<BirdSampling> sample;

	@JsonIgnore
	@OneToMany(mappedBy = "flock", fetch = FetchType.LAZY)
	private List<Provender> provender;

	@JsonIgnore
	@OneToMany(mappedBy = "flock", fetch = FetchType.LAZY)
	private List<Health> treatment;

	@OneToOne(mappedBy = "flock", fetch = FetchType.LAZY)
	private Product product;

}
