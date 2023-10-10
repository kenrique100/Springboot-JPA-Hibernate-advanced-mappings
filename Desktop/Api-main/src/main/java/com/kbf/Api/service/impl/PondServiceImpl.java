package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.model.Pond;
import com.kbf.Api.model.dto.PondDTO;
import com.kbf.Api.repository.PondRepository;
import com.kbf.Api.service.PondService;

@Service
public class PondServiceImpl implements PondService{

	
	@Autowired
	private PondRepository pondRepository;


	@Override
	public Pond createPond(Pond pond) {
		return pondRepository.save(pond);
	}

	public List<Pond> findAll(){
		//need a pond dto
		/*
		 * List<Pond> ponds = pondRepository.findAll(); List<Pond> pondsToview = new
		 * ArrayList<>(); ponds.forEach(pond->{ int fishInStock;
		 * pond.getStock().forEach(stock->{ if(!Objects.isNull(stock)) { //fishInStock =
		 * 0; } else { //fishInStock =(stock.getTotalStock()-stock.getMortality()); }
		 * 
		 * pondsToview.add(pond); }); //pond.setFishInStock(pond.getStock().) });
		 */
		return pondRepository.findAll();
	}

	@Override
	public Pond getPondById(int pondId) {
		Optional<Pond> pond = pondRepository.findById(pondId);
		//return pond.orElseThrow(() -> new NoSuchEntityException(String.format("Pond with id %s not found", pondId)));
		return pond.orElseThrow();
	}

	@Override
	public Pond updatePondById(int pondId, Pond pond) {
//		Optional<Pond> pondToUpdate = pondRepository.findById(pondId);
//		if (pondToUpdate.isPresent()) {
//			Pond originalPond = pondToUpdate.get();
//          System.out.println("Pons status is " + pond.isActive());
//            			
//			if ((Objects.nonNull(originalPond.getStock()))&&(pond.isActive()==true)) {
//				
//				throw new ProvenderEmptyException(" Pond is in Use.! " + '\n'
//						+ "and cannot be deactivated unless the containing the Fish stock is emptied");
//			}
//			
//			return pondRepository.save(pond);
//		}
		return pondRepository.save(pond);
	}

	@Override
	public String deletePondById(int pondId) {
		if(pondRepository.findById(pondId).isPresent()){
			pondRepository.deleteById(pondId);
			return "Pond removed successfully";
		}
		return "No such Pond in database";
	}

	@Override
	public Pond getPondByPondName(String pondName) {
		Optional<Pond> pond = Optional.ofNullable(pondRepository.findByPondName(pondName));
		return pond.orElse(null);
	}

	@Override
	public List<PondDTO> getAll() {
		//List<Pond> ponds = pondRepository.findAll();
//		List<PondDTO> pondsToview = new ArrayList<>();
//		ponds.forEach(pond->{
//			pond.getStock().forEach(stock->{
//				
//				if(Objects.isNull(stock)) {
//					pond.setFishInStock(0);
//				}
//				else {
//					pond.setFishInStock(stock.getTotalStock()-stock.getMortality());
//				}
//				PondDTO pondDto = new PondDTO(pond);
//				pondsToview.add(pondDto);
//			});
//			//pond.setFishInStock(pond.getStock().)
//		});
		return null;
	}


}
