package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.exception.ProvenderEmptyException;
import com.kbf.Api.model.BirdStock;
import com.kbf.Api.model.FishStock;
import com.kbf.Api.model.Provender;
import com.kbf.Api.repository.BirdStockRepository;
import com.kbf.Api.repository.FishStockRepository;
import com.kbf.Api.repository.ProvenderRepository;
import com.kbf.Api.service.ProvenderService;

@Service
public class ProvenderServiceImpl implements ProvenderService {

	@Autowired
	private ProvenderRepository mixedFeedRepo;

	@Autowired
	private BirdStockRepository birdRepo;

	@Autowired
	private FishStockRepository fishRepo;

	@Override
	public Provender createProvender(Provender mixedFeed) {
		validateProvender(mixedFeed);

		return mixedFeedRepo.save(mixedFeed);

	}

	@Override
	public List<Provender> getProvenderByBirdFlockId(int flockId) {
		return mixedFeedRepo.findAllByFlockId(flockId);

	}

	@Override
	public List<Provender> getProvenderByFishStockId(int stockId) {
		return mixedFeedRepo.findAllByStockId(stockId);
	}

	@Override
	public Provender updateProvender(Provender mixedFeed) {
		validateProvender(mixedFeed);
		mixedFeed.setQtyRemaining(mixedFeed.getQtyPrepared() - mixedFeed.getQtyUsed());
		if (mixedFeed.getQtyRemaining() == 0) {
			mixedFeed.setUsed(true);
		}
		return mixedFeedRepo.save(mixedFeed);
	}

	@Override
	public String deleteProvenderById(int mixedFeedId) {
		// check if associated before deleting
		if (mixedFeedRepo.findById(mixedFeedId).isPresent()) {
			mixedFeedRepo.deleteById(mixedFeedId);
			return "Item removed successfully";
		}
		return "No such item in database";
	}

	@Override
	public List<Provender> findAll() {
		return mixedFeedRepo.findAll();

	}

	@Override
	public Provender createProvenderByBirdFlockId(int flockId, Provender provender) {

		validateProvender(provender);
		Optional<BirdStock> stockToUpdate = birdRepo.findById(flockId);
		if (stockToUpdate.isPresent()) {
			BirdStock originalStock = stockToUpdate.get();
			provender.setFlock(originalStock);
			provender.setType(originalStock.getFlockType());
			provender.setQtyRemaining(provender.getQtyPrepared() - provender.getQtyUsed());
			return mixedFeedRepo.save(provender);
		}
		return null;
	}

	@Override
	public Provender createProvenderByFishStockId(int stockId, Provender provender) {

		validateProvender(provender);
		Optional<FishStock> stockToUpdate = fishRepo.findById(stockId);
		if (stockToUpdate.isPresent()) {
			FishStock originalStock = stockToUpdate.get();
			provender.setFishStock(originalStock);
			provender.setType(originalStock.getFishSpecy());
			provender.setQtyRemaining(provender.getQtyPrepared() - provender.getQtyUsed());
			return mixedFeedRepo.save(provender);
		}
		return null;
	}

	private void validateProvender(Provender mixedFeed) {
		if (Objects.isNull(mixedFeed.getBatchDate())) {
			throw new ProvenderEmptyException(" Date Prepared cannot be Null. Enter a Prepared Date!");
		}
		if (mixedFeed.getQtyPrepared() <= 0) {
			throw new ProvenderEmptyException(" Qty Prepared cannot be less than or equal to zero.!");
		}
	}

}
