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
@Table(name = "birdfeed ")
public class BirdFeed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int birdfeedId;

	private LocalDate feedingDate;
	private int qtyFed;
	private double avgBirdSize;
	@Column(columnDefinition = "integer default 2")
	private int timesFed;
	@Column(columnDefinition = "integer default 0")
	private int animalStock;
	@Column(columnDefinition = "integer default 0")
	private int reduced;
	@Column(columnDefinition = "integer default 0")
	private int mortality;
	private int provenderId;
	@Lob
	@Column
	private String feedingNotes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "flockId")
	private BirdStock flock;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "batchId")
	private Provender provender;

}
