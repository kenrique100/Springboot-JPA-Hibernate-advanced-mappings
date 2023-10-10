package com.kbf.Api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.service.FeedStockService;
import com.kbf.Api.model.FeedStock;
import com.kbf.Api.model.FishStock;
import com.kbf.Api.repository.FeedStockRepository;

@Service
public class FeedStockServiceImpl implements FeedStockService {

	@Autowired
	private FeedStockRepository feedStockRepo;
	
	@Override
	public FeedStock createFeedStock(FeedStock feedstock) {
		feedstock.setStockDate(LocalDate.now());
		feedstock.setStockRemaining(feedstock.getInStockQty()-feedstock.getQtyUsed());
		return feedStockRepo.save(feedstock);
	}
	@Override
	public List<FeedStock> findAll() {
		return feedStockRepo.findAll();
	}

	@Override
	public List<FeedStock> getFeedingStockById(int feedStockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedStock updateFeedStockById(int feedStockId, FeedStock feedstock) {
		feedstock.setStockDate(LocalDate.now());
		feedstock.setStockRemaining(feedstock.getInStockQty()-feedstock.getQtyUsed());
		return feedStockRepo.save(feedstock);
	}

	@Override
	public String deleteFeedStockById(int feedStockId) {
			if(feedStockRepo.findById(feedStockId).isPresent()){
				feedStockRepo.deleteById(feedStockId);
				return "Item removed successfully";
			}
			return "No such item in database";
		}
	@Override
	public FeedStock updateFeedStockUsage(int feedStockId, FeedStock feedStock) {
		Optional<FeedStock> stockToUpdate = feedStockRepo.findById(feedStockId);
		if (stockToUpdate.isPresent()) {
			FeedStock stock = stockToUpdate.get();
			stock.setBroilerFeedUsed(feedStock.getBroilerFeedUsed());
			stock.setDuckAndFowlFeedUsed(feedStock.getDuckAndFowlFeedUsed());
			stock.setPigFeedUsed(feedStock.getPigFeedUsed());
			stock.setFishFeedUsed(feedStock.getFishFeedUsed());
			stock.setOtherFeedUsed(feedStock.getOtherFeedUsed());
			stock.setRabbitFeedUsed(feedStock.getRabbitFeedUsed());
			
			stock.setQtyUsed(feedStock.getBroilerFeedUsed()+feedStock.getFishFeedUsed()+feedStock.getDuckAndFowlFeedUsed()
			+ feedStock.getPigFeedUsed()+feedStock.getRabbitFeedUsed()+feedStock.getOtherFeedUsed());
			
			return updateFeedStockById(feedStockId,stock);
		}
		return null;
	}
}
