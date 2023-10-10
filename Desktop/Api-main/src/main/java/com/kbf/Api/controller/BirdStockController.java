package com.kbf.Api.controller;

import static com.kbf.Api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.Api.model.BirdStock;
import com.kbf.Api.service.BirdStockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BirdStockController {

	@Autowired
	private BirdStockService birdService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/flocks")
	public List<BirdStock> getAllFlocks() {

		return birdService.findAll().stream()
				.filter(x->x.getStockRemaining()>0).collect(Collectors.toList());
	}
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/flock-history")
	public List<BirdStock> getAllFlockArchives() {

		return birdService.findAll().stream()
				.filter(x->x.getStockRemaining()<=0).collect(Collectors.toList());
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/flock")
	public BirdStock createStock(@RequestBody BirdStock stock) {

		return birdService.createStock(stock);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/flock/{id}")
	public String deleteStock(@PathVariable("id") int id) {

		return birdService.deleteStockById(id);
	}
	
	

}
