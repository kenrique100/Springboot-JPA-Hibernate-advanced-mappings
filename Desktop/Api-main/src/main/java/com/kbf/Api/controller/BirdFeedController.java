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

import com.kbf.Api.model.BirdFeed;
import com.kbf.Api.service.BirdFeedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BirdFeedController {

	@Autowired
	private BirdFeedService feedService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/birdfeed")
	@CrossOrigin
	public List<BirdFeed> getAll() {

		return feedService.findAll();
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/birdfeed/{flockId}")
	public List<BirdFeed> getFeedingByflockId(@PathVariable("flockId") int flockId) {

		return feedService.getBirdFeedingByFlockId(flockId);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/birdfeed/{id}")
	public BirdFeed updateFeeding(@PathVariable("id") int id, @RequestBody BirdFeed feed) throws Exception {

		return feedService.updateFeedingById(id, feed);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/birdfeed/{flockId}")
	@CrossOrigin
	public BirdFeed createFeed(@PathVariable("flockId") int flockId, @RequestBody BirdFeed feed) {

		return feedService.createFeeding(flockId, feed);
	}

	@DeleteMapping("/kbf/api/1.0/birdfeed/{id}")
	@CrossOrigin
	public String deletePond(@PathVariable("id") int id) {

		return feedService.deleteBirdFeedingById(id);
	}

}
