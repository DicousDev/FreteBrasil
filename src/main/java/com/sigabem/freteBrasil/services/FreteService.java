package com.sigabem.freteBrasil.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sigabem.freteBrasil.dto.FreteRequestDTO;
import com.sigabem.freteBrasil.dto.FreteResponseDTO;
import com.sigabem.freteBrasil.dto.ViaCepDTO;
import com.sigabem.freteBrasil.entities.Frete;
import com.sigabem.freteBrasil.repositories.FreteRepository;

@Service
public class FreteService {

	private final Double valorFretePorKg = 1D;
	
	@Autowired
	private FreteRepository freteRepository;
	
	public FreteResponseDTO frete(FreteRequestDTO freteRequest) {
		
		Double totalFrete = freteRequest.getPeso() * valorFretePorKg;
		ViaCepDTO origem = buscarCEP(freteRequest.getCepOrigem());
		ViaCepDTO destino = buscarCEP(freteRequest.getCepDestino());

		Calendar entregaPrevista = Calendar.getInstance();
		if(IsCidadesIguais(origem.ddd, destino.ddd)) {
			entregaPrevista.add(Calendar.DATE, 1);
			totalFrete = aplicarDesconto(totalFrete, 50D);
		}
		else if(IsEstadosIguais(origem.uf, destino.uf)) {
			entregaPrevista.add(Calendar.DATE, 3);
			totalFrete = aplicarDesconto(totalFrete, 75D);
		}
		else if(!IsEstadosIguais(origem.uf, destino.uf)) {
			entregaPrevista.add(Calendar.DATE, 10);
		}
		
		
		Date dataPrevista = entregaPrevista.getTime();
		Calendar dataHoje = Calendar.getInstance();
		Frete frete = new Frete(freteRequest.getCepOrigem(), freteRequest.getCepDestino(), freteRequest.getNomeDestinatario(), totalFrete, dataPrevista, dataHoje.getTime());
		freteRepository.save(frete);
		
		FreteResponseDTO freteResponse = new FreteResponseDTO(freteRequest.getCepOrigem(), freteRequest.getCepDestino(), totalFrete, dataPrevista);
		return freteResponse;
	}
	
	private Double aplicarDesconto(Double valor, Double porcentagem) {
		Double desconto = calcularPorcentagem(valor, porcentagem);
		return valor - desconto;
	}
	
	private Double calcularPorcentagem(Double valor, Double porcentagem) {
		return (porcentagem / 100) * valor;
	}
	
	private ViaCepDTO buscarCEP(String cep) {
		String viaCepUri = String.format("https://viacep.com.br/ws/" + cep + "/json/");
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(viaCepUri, ViaCepDTO.class);
	}
	
	private Boolean IsCidadesIguais(String dddOrigem, String dddDestino) {
		return dddOrigem.equalsIgnoreCase(dddDestino);
	}
	
	private Boolean IsEstadosIguais(String estadoOrigem, String estadoDestino) {
		return estadoOrigem.equalsIgnoreCase(estadoDestino);
	}
}