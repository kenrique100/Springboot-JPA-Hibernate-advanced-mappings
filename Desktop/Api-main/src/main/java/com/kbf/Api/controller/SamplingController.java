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

import com.kbf.Api.model.FishSampling;
import com.kbf.Api.service.FishSamplingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SamplingController {

	@Autowired
	private FishSamplingService sampleService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/sample")
	@CrossOrigin
	public List<FishSampling> getAll() {

		return sampleService.findAll();
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/sample/{id}")
	@CrossOrigin
	public List<FishSampling> getSamplingByStockId(@PathVariable("id") int id) {

		return sampleService.getSamplesByStockId(id);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PutMapping("/kbf/api/1.0/sample/{id}")
	@CrossOrigin
	public FishSampling updateSample(@PathVariable("id") int id, @RequestBody FishSampling sample) {
		System.out.println("Sample and Id is " + id + sample);

		return sampleService.updateSampleById(id, sample);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/sample/{stockId}")
	@CrossOrigin
	public FishSampling createSample(@PathVariable("stockId") int stockId, @RequestBody FishSampling sample) {

		return sampleService.createSample(stockId, sample);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/sample/{id}")
	@CrossOrigin
	public String deleteSample(@PathVariable("id") int id) {

		return sampleService.deleteSamplingById(id);
	}

}
