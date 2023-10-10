package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.exception.ProvenderEmptyException;
import com.kbf.Api.model.FishFeed;
import com.kbf.Api.model.FishStock;
import com.kbf.Api.model.Provender;
import com.kbf.Api.model.dto.FeedingDTO;
import com.kbf.Api.repository.FishFeedRepository;
import com.kbf.Api.repository.FishStockRepository;
import com.kbf.Api.repository.ProvenderRepository;
import com.kbf.Api.service.FishFeedService;
import com.kbf.Api.service.ProvenderService;

@Service
public class FishFeedImpl implements FishFeedService {

	@Autowired
	private FishFeedRepository fishFeedRepo;

	@Autowired
	private FishStockRepository stockRepository;

	@Autowired
	private ProvenderRepository mixedFeedRepo;

	@Autowired
	private ProvenderService provService;

	public List<FishFeed> findAll() {
		return fishFeedRepo.findAll();
	}

	@Override
	public List<FishFeed> getFishFeedingByStockId(int stockId) {
		return fishFeedRepo.findBirdFeedByFlockId(stockId);
	}

	@Override
	public List<FishFeed> getFeedingStockById(int stockId) {
		Optional<FishStock> stock = stockRepository.findById(stockId);
		if (stock.isPresent()) {
			FishStock originalStock = stock.get();
			List<FishFeed> feedings = originalStock.getFeed().stream().collect(Collectors.toList());
			return feedings;
		}
		return null;
	}

	@Override
	public List<FeedingDTO> getFeedingDTOStockById(int stockId) {
		Optional<FishStock> stock = stockRepository.findById(stockId);
		if (stock.isPresent()) {
			FishStock originalStock = stock.get();
			List<FeedingDTO> feedingDTOs = originalStock.getFeed().stream().map(FeedingDTO::new)
					.collect(Collectors.toList());
			return feedingDTOs;
		}
		return null;
	}

	@Override
	public FishFeed updateFeedingById(int feedingId, FishFeed feeding) {
		Optional<FishFeed> feedingToUpdate = fishFeedRepo.findById(feedingId);
		if (feedingToUpdate.isPresent()) {
			FishFeed originalFeeding = feedingToUpdate.get();

			if (feeding.getProvenderId() <= 0) {
				throw new ProvenderEmptyException(" Cannot update feeding record without a Provender Id.! " + '\n'
						+ "To proceed, refer to the Provender Table and associate the Feeding record to a valid provender batchId for this flock");
			} else {
				originalFeeding.setProvenderId(feeding.getProvenderId());

				Optional<Provender> provenderToUpdate = mixedFeedRepo.findById(feeding.getProvenderId());
				if (provenderToUpdate.isPresent()) {
					Provender entryFeed = provenderToUpdate.get();

					if (originalFeeding.getQtyFed() != feeding.getQtyFed()) {
						if (entryFeed.getQtyRemaining() <= 0) {
							throw new ProvenderEmptyException(
									" Update Failed. No Qty Remaining in this provender record! ");
						}
						if (feeding.getQtyFed() > entryFeed.getQtyRemaining()) {
							throw new ProvenderEmptyException(
									" Update Failed. Qty Fed is greater than provender stock remaining! ");
						}

					}

					if (Objects.nonNull(feeding.getQtyFed()) && feeding.getQtyFed() != 0) {
						originalFeeding.setQtyFed(feeding.getQtyFed());
					}
					if (Objects.nonNull(feeding.getAvgFishSize()) && feeding.getAvgFishSize() != 0) {
						originalFeeding.setAvgFishSize(feeding.getAvgFishSize());
					}
					if (Objects.nonNull(feeding.getFishInPond()) && feeding.getFishInPond() != 0) {
						originalFeeding.setFishInPond(feeding.getFishInPond());
					}
					if (Objects.nonNull(feeding.getFeedingNotes())) {
						originalFeeding.setFeedingNotes(feeding.getFeedingNotes());
					}
					if (Objects.nonNull(feeding.getTimesFed()) && feeding.getTimesFed() != 0) {
						originalFeeding.setTimesFed(feeding.getTimesFed());
					}
					if (Objects.nonNull(feeding.getFeedingDate())) {
						originalFeeding.setFeedingDate(feeding.getFeedingDate());
					}
					originalFeeding.setReduced(feeding.getReduced());
        			originalFeeding.setMortality(feeding.getMortality());
					entryFeed.setQtyUsed(entryFeed.getQtyUsed() + feeding.getQtyFed());
					provService.updateProvender(entryFeed);

				} else {
					throw new ProvenderEmptyException(" Provender Table not found.! " + '\n'
							+ "To proceed, refer to the Provender Table and associate the Feeding record to a valid provender batchId for this flock");

				}
			}
			return fishFeedRepo.save(originalFeeding);
		}
		return null;
	}

	@Override
	public FishFeed createFeeding(int stockId, FishFeed feeding) {
		validateFeedingDateQtyFed(feeding);

		Optional<FishStock> stock = stockRepository.findById(stockId);
		if (stock.isPresent()) {
			FishStock originalStock = stock.get();
			if (feeding.getProvenderId() <= 0) {
				throw new ProvenderEmptyException(" Cannot update feeding record without a Provender Id.! " + '\n'
						+ "To proceed, refer to the Provender Table and associate the Feeding record to a valid provender batchId for this flock");
			} else {
				feeding.setProvenderId(feeding.getProvenderId());
				Optional<Provender> provenderToUpdate = mixedFeedRepo.findById(feeding.getProvenderId());
				if (provenderToUpdate.isPresent()) {
					Provender entryFeed = provenderToUpdate.get();

					if (entryFeed.getQtyRemaining() <= 0) {
						throw new ProvenderEmptyException(
								" Update Failed. No Qty Remaining in this provender record! ");
					}
					if (feeding.getQtyFed() > entryFeed.getQtyRemaining()) {
						throw new ProvenderEmptyException(
								" Update Failed. Qty Fed is greater than provender stock remaining! ");
					}

					entryFeed.setQtyUsed(entryFeed.getQtyUsed() + feeding.getQtyFed());
					provService.updateProvender(entryFeed);

				} else {
					throw new ProvenderEmptyException(" Provender Table not found.! " + '\n'
							+ "To proceed, refer to the Provender Table and associate the Feeding record to a valid provender batchId for this flock");

				}
				feeding.setFishStock(originalStock);
				return fishFeedRepo.save(feeding);
			}

		}
		return null;
	}

	@Override
	public String deleteFeedingById(int feedingId) {

		Optional<FishFeed> feedingToUpdate = fishFeedRepo.findById(feedingId);
		if (feedingToUpdate.isPresent()) {
			FishFeed originalFeeding = feedingToUpdate.get();
			Optional<Provender> provenderToUpdate = mixedFeedRepo.findById(originalFeeding.getProvenderId());
			if (provenderToUpdate.isPresent()) {

				Provender entryFeed = provenderToUpdate.get();

				entryFeed.setQtyUsed(entryFeed.getQtyUsed() - originalFeeding.getQtyFed());
				provService.updateProvender(entryFeed);
			}
		}
		if (fishFeedRepo.findById(feedingId).isPresent()) {
			fishFeedRepo.deleteById(feedingId);
			return "Feeding record removed successfully";
		}
		return "No such Feeding Record in database";

	}

	private void validateFeedingDateQtyFed(FishFeed feeding) {
		if (Objects.isNull(feeding.getFeedingDate())) {
			throw new ProvenderEmptyException(" Feeding Date cannot be Null. Enter a Feeding Date!");
		}
		if (feeding.getQtyFed() <= 0) {
			throw new ProvenderEmptyException(" Qty Fed cannot be less than or equal to zero.!");
		}
	}

}
