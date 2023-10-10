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

import com.kbf.Api.exception.EntityNotFoundException;
import com.kbf.Api.model.Pond;
import com.kbf.Api.service.PondService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PondController {

	@Autowired
	private PondService pondService;
	
	
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping("/kbf/api/1.0/pond") 
	public List<Pond> getAllPonds() {
		
		return pondService.findAll();
	}
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PostMapping("/kbf/api/1.0/pond") @CrossOrigin
	public Pond createPond(@RequestBody Pond pond) {
        
		return pondService.createPond(pond);
	}
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping("/kbf/api/1.0/pond/{id}") @CrossOrigin
	public Pond getPond(@PathVariable("id") int id) throws EntityNotFoundException {

		return pondService.getPondById(id);
	}
	
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})	
	@PutMapping("/kbf/api/1.0/pond/{id}") 
	public Pond updatePond(@PathVariable("id") int id, @RequestBody Pond pond) {

		return pondService.updatePondById(id, pond);
	}
	
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping ("/kbf/api/1.0/pond/{id}") @CrossOrigin
	public String deletePond(@PathVariable("id") int id) {


		return pondService.deletePondById(id);
	}

}
