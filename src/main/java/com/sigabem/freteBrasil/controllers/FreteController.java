package com.sigabem.freteBrasil.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigabem.freteBrasil.dto.FreteRequestDTO;
import com.sigabem.freteBrasil.dto.FreteResponseDTO;
import com.sigabem.freteBrasil.services.FreteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/frete")
public class FreteController {

	@Autowired
	private FreteService freteService;
	
	@ApiOperation(value = "Buscar frete por ID",code = 200, response = FreteResponseDTO.class)
	@GetMapping(value = "/{idFrete}")
	public ResponseEntity<?> getFreteById(@PathVariable Long idFrete) {
		
		try {
			FreteResponseDTO response = freteService.findFreteById(idFrete);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Fazer frete",code = 201, response = FreteResponseDTO.class)
	@PostMapping
	public ResponseEntity<?> pedirFrete(@Valid @RequestBody FreteRequestDTO frete) {
		
		try {
			FreteResponseDTO response = freteService.pedirFrete(frete);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}
	}
}
