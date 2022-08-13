package com.sigabem.freteBrasil.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sigabem.freteBrasil.dto.FreteRequestDTO;
import com.sigabem.freteBrasil.dto.FreteResponseDTO;
import com.sigabem.freteBrasil.services.FreteService;

@RestController
@RequestMapping(value = "/frete")
public class FreteController {

	@Autowired
	private FreteService freteService;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public FreteResponseDTO getFrete(@RequestBody FreteRequestDTO frete) {
		return freteService.frete(frete);
	}
}
