package com.sigabem.freteBrasil.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sigabem.freteBrasil.dto.ViaCepDTO;

@Service
public class CepService {

	public ViaCepDTO buscarCEP(String cep) throws Exception {
		String viaCepUri = String.format("https://viacep.com.br/ws/" + cep + "/json/");
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(viaCepUri, ViaCepDTO.class);
	}
}
