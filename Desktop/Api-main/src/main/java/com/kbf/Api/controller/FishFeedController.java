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

import com.kbf.Api.model.FishFeed;
import com.kbf.Api.service.FishFeedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FishFeedController {

	@Autowired
	private FishFeedService fishFeedService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/feed")
	@CrossOrigin
	public List<FishFeed> getAll() {

		return fishFeedService.findAll();
	}

		
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/fishfeed/{stockId}")
	public FishFeed createFeed(@PathVariable("stockId") int stockId, @RequestBody FishFeed feed) {

		return fishFeedService.createFeeding(stockId, feed);
	}
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/fishfeed/{stockId}")
	public List<FishFeed> getFeedingByStockId(@PathVariable("stockId") int stockId) {

		return fishFeedService.getFeedingStockById(stockId);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/fishfeed/{id}")
	public FishFeed updateFeeding(@PathVariable("id") int id, @RequestBody FishFeed fishFeed) throws Exception {

		return fishFeedService.updateFeedingById(id, fishFeed);
	}

	

	@DeleteMapping("/kbf/api/1.0/fishfeed/{id}")
	public String deleteFishFeed(@PathVariable("id") int id) {

		return fishFeedService.deleteFeedingById(id);
	}

}
