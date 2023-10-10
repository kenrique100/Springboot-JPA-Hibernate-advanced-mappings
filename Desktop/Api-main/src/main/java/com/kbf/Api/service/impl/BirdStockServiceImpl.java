package com.kbf.Api.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.exception.ProvenderEmptyException;
import com.kbf.Api.model.BirdStock;
import com.kbf.Api.repository.BirdStockRepository;
import com.kbf.Api.service.BirdStockService;

@Service
public class BirdStockServiceImpl implements BirdStockService {

	@Autowired
	private BirdStockRepository birdRepo;

	@Override
	public BirdStock createStock(BirdStock stock) {

		if (Objects.isNull(stock.getStockDate())) {
			throw new ProvenderEmptyException(" Stock Date cannot be Null. Enter a Stock Date!");
		}
		if (stock.getNbrOfBirds() == 0) {
			throw new ProvenderEmptyException(" Nbr of birds cannot be zero.!");
		}

		if (Objects.nonNull(stock.getStockDate())) {
			int diffInDays = (int) ChronoUnit.DAYS.between(stock.getStockDate(), LocalDate.now());
			stock.setNbrOfDays(diffInDays);
		}
		stock.setStockRemaining(stock.getNbrOfBirds() - (stock.getReduction() + stock.getMortality()));
		if (stock.getStockRemaining() <= 0) {
			stock.setSoldOut(true);

		}

		return birdRepo.save(stock);
	}

	@Override
	public List<BirdStock> findAll() {

		// List<BirdStock> birdStocks = birdRepo.findAll();

//		Iterator<BirdStock> iterator = birdStocks.iterator();
//		while (iterator.hasNext()) {
//			BirdStock stock = iterator.next(); // must be called before you can call i.remove()
//			if (Objects.nonNull(stock.getStockDate())) {
//				int diffInDays = (int) ChronoUnit.DAYS.between(stock.getStockDate(), LocalDate.now());
//
//				stock.setNbrOfDays(diffInDays);
//
//			}
//			stock.setStockRemaining(stock.getNbrOfBirds() - (stock.getReduction() + stock.getMortality()));
//
//			if (stock.getStockRemaining() <= 0) {
//				// call to set sold out and send to archives : this should be done at update
//				stock.setSoldOut(true);
//				iterator.remove();
//			}
//
//		}
		
		return birdRepo.findAll();
	}

	@Override
	public BirdStock getStockById(int stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BirdStock updateStockById(int stockId, BirdStock stock) {
		Optional<BirdStock> stockToUpdate = birdRepo.findById(stockId);
		if (stockToUpdate.isPresent()) {
			BirdStock originalStock = stockToUpdate.get();

			if (Objects.nonNull(stock.getStockDate())) {
				int diffInDays = (int) ChronoUnit.DAYS.between(stock.getStockDate(), LocalDate.now());
				originalStock.setNbrOfDays(diffInDays);
			}
			originalStock.setStockRemaining(stock.getNbrOfBirds() - (stock.getReduction() + stock.getMortality()));
			if (stock.getStockRemaining() >= stock.getNbrOfBirds()) {
				originalStock.setSoldOut(true);

			}
			if (Objects.nonNull(stock.getFlockType()) && !"".equalsIgnoreCase(stock.getFlockType())) {
				originalStock.setFlockType(stock.getFlockType());
			}
			if (Objects.nonNull(stock.getPurpose()) && !"".equalsIgnoreCase(stock.getPurpose())) {
				originalStock.setPurpose(stock.getPurpose());
			}
			if (Objects.nonNull(stock.getNbrOfBirds()) && stock.getNbrOfBirds() != 0) {
				originalStock.setNbrOfBirds(stock.getNbrOfBirds());
			}
			if (Objects.nonNull(stock.getMortality())) {
				originalStock.setMortality(stock.getMortality());
			}
			if (Objects.nonNull(stock.getReduction())) {
				originalStock.setReduction(stock.getReduction());
			}
			if (Objects.nonNull(stock.getStockDate())) {
				originalStock.setStockDate(stock.getStockDate());

			}

			return birdRepo.save(originalStock);

		}
		return null;
	}

	@Override
	public String deleteStockById(int stockId) {
		if (birdRepo.findById(stockId).isPresent()) {
			birdRepo.deleteById(stockId);
			return "Item removed successfully";
		}
		return "No such item in database";
	}

	
}
