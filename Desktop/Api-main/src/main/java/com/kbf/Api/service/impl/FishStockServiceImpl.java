package com.kbf.Api.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.exception.ProvenderEmptyException;
import com.kbf.Api.model.FishStock;
import com.kbf.Api.repository.FishStockRepository;
import com.kbf.Api.service.FishStockService;

@Service
public class FishStockServiceImpl implements FishStockService {

	@Autowired
	private FishStockRepository stockRepository;
	
	
	@Override
	public FishStock createStock(FishStock stock) {
		//FishStock stockToSave = new FishStock();
		if (Objects.isNull(stock.getStockDate())) {
			throw new ProvenderEmptyException(" Stock Date cannot be Null. Enter a Stock Date!");
		}
		if (stock.getTotalStock() == 0) {
			throw new ProvenderEmptyException(" Total Stock cannot be zero.!");
		}
		
			int diffInDays = (int) ChronoUnit.DAYS.between(stock.getStockDate(), LocalDate.now());
			stock.setNbrOfDays(diffInDays);
			
		   stock.setStockRemaining(stock.getTotalStock() - (stock.getReduction() + stock.getMortality()));
			if (stock.getStockRemaining() <= 0) {
				stock.setSoldOut(true);
	
			}
			else {
				stock.setSoldOut(false);
			}
		
//		if (Objects.nonNull(stock.getFishPondId())) {
//			Optional<Pond> pondToUpdate = pondRepository.findById(stock.getFishPondId());
//			if (pondToUpdate.isPresent()) {
//				Pond pond = pondToUpdate.get();
//
//				
//
//				pond.setFishInStock(stock.getTotalStock() -(stock.getReduction() + stock.getMortality()));
//				
//				stock.setFishPondId(stock.getFishPondId());
//				if(stock.getStockRemaining() >= 0) {
//					pond.setActive(false);
//				}
//				else {
//					pond.setActive(true);
//				}
////				if (pond.isActive()) {
////					throw new ProvenderEmptyException(" Pond is already in Use.! " + '\n'
////							+ "To proceed, refer to the Pond Table and associate the Fish Stock record to an unused Pond ID");
////				}
//			//	stockToSave.setPond(pond);
//
//			} else {
//				throw new ProvenderEmptyException(" Pond ID not found.! " + '\n'
//						+ "To proceed, refer to the Pond Table and associate the Fish Stock record to a valid Pond ID");
//			}
//		}

		return stockRepository.save(stock);

	}

	@Override
	public List<FishStock> findAll() {
		

		return stockRepository.findAll();
	}

	@Override
	public FishStock getStockById(int stockId) {
		Optional<FishStock> stock = stockRepository.findById(stockId);

		return stock.orElseThrow();
	}

	@Override
	public FishStock updateStockById(int stockId, FishStock stock) {
		//handle case where error on pond id being changed
		return createStock(stock);
		
	}

		

	@Override
	public String deleteStockById(int stockId) {
		Optional<FishStock> stockToDelete = stockRepository.findById(stockId);
		if (stockToDelete.isPresent()) {
			FishStock originalStock = stockToDelete.get();
			if (Objects.isNull(originalStock.getFeed()) && (Objects.isNull(originalStock.getSample()))) {
				stockRepository.deleteById(stockId);
				return "Stock removed successfully";
			}
		}
		return "Cannot delete stock. Stock does not exist in Database " + "or has associated Feeding or Sample records";
	}

	
}
