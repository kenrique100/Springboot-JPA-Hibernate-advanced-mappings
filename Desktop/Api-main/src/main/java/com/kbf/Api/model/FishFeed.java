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
@Table(name = "fishfeed")
public class FishFeed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedingId;

	private LocalDate feedingDate;
	private int qtyFed;
	@Column(columnDefinition = "integer default 0")
	private double avgFishSize;
	@Column(columnDefinition = "integer default 0")
	private int pelletSize;
	@Column(columnDefinition = "integer default 2")
	private int timesFed;
	@Column(columnDefinition = "integer default 0")
	private int fishInPond;
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
	@JoinColumn(name = "stockId")
	private FishStock fishStock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "batchId")
	private Provender provender;

}
