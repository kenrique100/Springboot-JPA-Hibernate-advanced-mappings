package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.exception.ProvenderEmptyException;
import com.kbf.Api.model.BirdFeed;
import com.kbf.Api.model.BirdStock;
import com.kbf.Api.model.Provender;
import com.kbf.Api.repository.BirdFeedRepository;
import com.kbf.Api.repository.BirdStockRepository;
import com.kbf.Api.repository.ProvenderRepository;
import com.kbf.Api.service.BirdFeedService;
import com.kbf.Api.service.ProvenderService;

@Service
public class BirdFeedServiceImpl implements BirdFeedService {

	@Autowired
	BirdFeedRepository birdFeedRepo;

	@Autowired
	BirdStockRepository flockRepo;

	@Autowired
	private ProvenderRepository mixedFeedRepo;

	@Autowired
	private ProvenderService provService;

	@Override
	public BirdFeed createFeeding(int flockId, BirdFeed feeding) {
		validateFeedingDateQtyFed(feeding);

		Optional<BirdStock> flock = flockRepo.findById(flockId);
		if (flock.isPresent()) {
			BirdStock originalStock = flock.get();
			feeding.setFlock(originalStock);
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
				
				return birdFeedRepo.save(feeding);
			}

		}
		return null;
	}

	@Override
	public List<BirdFeed> findAll() {
		return birdFeedRepo.findAll();
	}

	@Override
	public List<BirdFeed> getBirdFeedingByFlockId(int flockId) {
		return birdFeedRepo.findBirdFeedByFlockId(flockId);
	}

	@Override
	public BirdFeed updateFeedingById(int feedingId, BirdFeed feeding) throws Exception {

		validateFeedingDateQtyFed(feeding);

		Optional<BirdFeed> feedingToUpdate = birdFeedRepo.findById(feedingId);
		if (feedingToUpdate.isPresent()) {
			BirdFeed originalFeeding = feedingToUpdate.get();

			
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
        			if (Objects.nonNull(feeding.getAvgBirdSize()) && feeding.getAvgBirdSize() != 0) {
        				originalFeeding.setAvgBirdSize(feeding.getAvgBirdSize());
        			}
        			if (Objects.nonNull(feeding.getFeedingNotes())) {
        				originalFeeding.setFeedingNotes(feeding.getFeedingNotes());
        			}
        			if (Objects.nonNull(feeding.getAnimalStock()) && feeding.getAnimalStock() != 0) {
        				originalFeeding.setAnimalStock(feeding.getAnimalStock());
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
			return birdFeedRepo.save(originalFeeding);
		}
		return null;
	}

	@Override
	public String deleteBirdFeedingById(int feedingId) {
		Optional<BirdFeed> feedingToUpdate = birdFeedRepo.findById(feedingId);
		if (feedingToUpdate.isPresent()) {
			BirdFeed originalFeeding = feedingToUpdate.get();
			Optional<Provender> provenderToUpdate = mixedFeedRepo.findById(originalFeeding.getProvenderId());
			if (provenderToUpdate.isPresent()) {

				Provender entryFeed = provenderToUpdate.get();

				entryFeed.setQtyUsed(entryFeed.getQtyUsed() - originalFeeding.getQtyFed());
				provService.updateProvender(entryFeed);
			}
		}
		if (birdFeedRepo.findById(feedingId).isPresent()) {
			birdFeedRepo.deleteById(feedingId);
			return "Feeding record removed successfully";
		}
		return "No such Feeding Record in database";
	}

	private void validateFeedingDateQtyFed(BirdFeed feeding) {
		if (Objects.isNull(feeding.getFeedingDate())) {
			throw new ProvenderEmptyException(" Feeding Date cannot be Null. Enter a Feeding Date!");
		}
		if (feeding.getQtyFed() <= 0) {
			throw new ProvenderEmptyException(" Qty Fed cannot be less than or equal to zero.!");
		}
	}

}
