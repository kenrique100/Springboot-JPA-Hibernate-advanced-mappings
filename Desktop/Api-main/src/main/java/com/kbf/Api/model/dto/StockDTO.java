package com.kbf.Api.model.dto;

import java.time.LocalDate;

import com.kbf.Api.model.FishStock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

	private int stockId;
	private String fishSpecy;
	private LocalDate stockDate;
	private LocalDate dateEmptied;
	private int totalStock;
	private int mortality;
	private int stockRemaining;
	private int samplesCollected;
	private String pondUsed;

	// private List<FeedingDTO> feedings = new ArrayList<>();
	// private List<SamplingDTO> samples = new ArrayList<>();
	// private PondDTO pondInUse ;

	public StockDTO(FishStock stock) {
		this.setStockId(stock.getStockId());
		this.setFishSpecy(stock.getFishSpecy());
		this.setStockDate(stock.getStockDate());
		this.setTotalStock(stock.getTotalStock());
		this.setMortality(stock.getMortality());
		this.setStockRemaining(stock.getTotalStock() - stock.getMortality());
		// List<FeedingDTO> feedingDTOs =
		// stock.getFeed().stream().map(FeedingDTO::new).collect(Collectors.toList());
		// List<SamplingDTO> sampleDTOs =
		// stock.getSample().stream().map(SamplingDTO::new).toList();
		// this.setFeedings(feedingDTOs);
		// this.setSamples(sampleDTOs);
		// this.setSamplesCollected(sampleDTOs.size());
		// this.pondInUse = new PondDTO(stock.getPond());
		// this.setPondUsed(stock.getPond().getPondName());
	}

}
