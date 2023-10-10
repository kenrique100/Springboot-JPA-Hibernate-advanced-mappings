package com.kbf.Api.controller;

import static com.kbf.Api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.Api.model.Provender;
import com.kbf.Api.service.ProvenderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProvenderController {

	@Autowired
	private ProvenderService mixFeedService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/provender")
	@CrossOrigin
	public List<Provender> getAll() {

		return mixFeedService.findAll();
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/birdprovender/{flockId}")
	public List<Provender> getMixedFeedByBirdFlockId(@PathVariable("flockId") int flockId) {

		return mixFeedService.getProvenderByBirdFlockId(flockId);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/fishprovender/{stockId}")
	public List<Provender> getMixedFeedByFishStockId(@PathVariable("stockId") int stockId) {

		return mixFeedService.getProvenderByFishStockId(stockId);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/provender/{id}")
	public Provender updateMixedFeed(@RequestBody Provender mixedFeed) {

		return mixFeedService.updateProvender(mixedFeed);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/provender")
	public Provender createFeed(@RequestBody Provender mixedFeed) {

		return mixFeedService.createProvender(mixedFeed);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/birdprovender/{flockId}")
	public Provender createMixedFeedByBirdFlockId(@PathVariable("flockId") int flockId,
			@RequestBody Provender mixedFeed) {

		return mixFeedService.createProvenderByBirdFlockId(flockId, mixedFeed);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/fishprovender/{stockId}")
	public Provender createMixedFeedByFishStockId(@PathVariable("stockId") int stockId,
			@RequestBody Provender mixedFeed) {

		return mixFeedService.createProvenderByFishStockId(stockId, mixedFeed);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/provender/{id}")
	public String deleteMixedFeed(@PathVariable("id") int id) {

		return mixFeedService.deleteProvenderById(id);
	}

}
