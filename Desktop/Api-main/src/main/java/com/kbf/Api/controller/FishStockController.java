package com.kbf.Api.controller;

import static com.kbf.Api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.Api.model.FishStock;
import com.kbf.Api.service.FishStockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FishStockController {

	@Autowired
	private FishStockService stockService;

//	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
//	@GetMapping("/kbf/api/1.0/fishstock")
//	@CrossOrigin
//	public List<FishStock> getAllPonds() {
//
//		return stockService.findAll();
//				//stockService.findAll().stream().map(StockDTO::new).collect(Collectors.toList());
//	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/fishstock")
	public List<FishStock> getAllStocks() {

		return stockService.findAll().stream()
				.filter(x->x.getStockRemaining()>0).collect(Collectors.toList());
	}
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/fishstock-history")
	public List<FishStock> getStockHistory() {

		return stockService.findAll().stream()
				.filter(x->x.getStockRemaining()<=0).collect(Collectors.toList());
	}


//	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
//	@PutMapping("/kbf/api/1.0/stock/{id}")
//	@CrossOrigin
//	public StockDTO updatePond(@PathVariable("id") int id, @RequestBody StockDTO stock) {
//		FishStock updatedStock = stockService.updateStockById(id, stock);
//		StockDTO updatedDTOStock = new StockDTO(updatedStock);
//		return updatedDTOStock;
//	}
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/fishstock/{stockId}")
	public FishStock UpdateFishStock(@PathVariable("stockId") int stockId,@RequestBody FishStock  stock) {

		return stockService.updateStockById(stockId, stock);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/fishstock")
	public FishStock createFishStock(@RequestBody FishStock  stock) {

		return stockService.createStock(stock);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/fishstock/{id}")
	public String deletePond(@PathVariable("id") int id) {

		return stockService.deleteStockById(id);
	}

}
