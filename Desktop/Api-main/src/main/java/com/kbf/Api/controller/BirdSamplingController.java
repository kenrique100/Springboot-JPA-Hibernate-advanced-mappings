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

import com.kbf.Api.model.BirdSampling;
import com.kbf.Api.service.BirdSamplingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BirdSamplingController {

	@Autowired
	private BirdSamplingService sampleService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/birdsample")
	@CrossOrigin
	public List<BirdSampling> getAll() {

		return sampleService.findAll();
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/birdsample/{id}")
	@CrossOrigin
	public List<BirdSampling> getSamplingByStockId(@PathVariable("id") int id) {

		return sampleService.getSamplesByFlockId(id);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/birdsample/{sampleId}/{flockType}")
	public BirdSampling updateSample(@PathVariable("sampleId") int sampleId, @PathVariable("flockType") String flockType,
			@RequestBody BirdSampling sample) {
		 
		sample.setBirdType(flockType);

		return sampleService.updateBirdSampleById(sampleId, sample);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/birdsample/{flockId}/{flockType}")
	public BirdSampling createSample(@PathVariable("flockId") int flockId,@PathVariable("flockType") String flockType,
			@RequestBody BirdSampling sample) {
		 sample.setBirdType(flockType);
		return sampleService.createSample(flockId, sample);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/birdsample/{id}")
	@CrossOrigin
	public String deleteSample(@PathVariable("id") int id) {

		return sampleService.deleteBirdSamplingById(id);
	}

}
