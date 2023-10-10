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

import com.kbf.Api.model.FeedStock;
import com.kbf.Api.service.FeedStockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FeedStockController {

	@Autowired
	private FeedStockService feedStockService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/feedstock")
	@CrossOrigin
	public List<FeedStock> getAll() {

		return feedStockService.findAll();
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/feedstock/{id}")
	public FeedStock updateFeedStock(@PathVariable("id") int id, @RequestBody FeedStock feedStock) {

		return feedStockService.updateFeedStockById(id, feedStock);
	}
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/feedstock/usage/{id}")
	public FeedStock updateFeedStockUsage(@PathVariable("id") int id, @RequestBody FeedStock feedStock) {

		return feedStockService.updateFeedStockUsage(id, feedStock);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/feedstock")
	public FeedStock createFeed(@RequestBody FeedStock feedStock) {

		return feedStockService.createFeedStock(feedStock);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/feedstock/{id}")
	public String deletePond(@PathVariable("id") int id) {

		return feedStockService.deleteFeedStockById(id);
	}

}
