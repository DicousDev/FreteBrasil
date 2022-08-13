package com.sigabem.freteBrasil.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigabem.freteBrasil.dto.FreteRequestDTO;
import com.sigabem.freteBrasil.dto.FreteResponseDTO;
import com.sigabem.freteBrasil.dto.ViaCepDTO;
import com.sigabem.freteBrasil.entities.Frete;
import com.sigabem.freteBrasil.repositories.FreteRepository;
import com.sigabem.freteBrasil.utils.Utils;

@Service
public class FreteService {

	private final Double valorFretePorKg = 1D;
	
	@Autowired
	private FreteRepository freteRepository;
	
	@Autowired
	private CepService cepService;
	
	public List<FreteResponseDTO> findFretesAll() {
		return freteRepository.findAll().stream().map(frete -> new FreteResponseDTO(frete)).collect(Collectors.toList());
	}
	
	public FreteResponseDTO findFreteById(Long idFrete) {
		
		Optional<Frete> frete = freteRepository.findById(idFrete);
		if(frete.isEmpty()) {
			throw new RuntimeException("O frete não foi encontrado.");
		}
		
		return new FreteResponseDTO(frete.get());
	}
	
	public FreteResponseDTO pedirFrete(FreteRequestDTO freteRequest) throws Exception {
		
		Double totalFrete = freteRequest.getPeso() * valorFretePorKg;
		ViaCepDTO origem = cepService.buscarCEP(freteRequest.getCepOrigem());
		ViaCepDTO destino = cepService.buscarCEP(freteRequest.getCepDestino());

		Calendar entregaPrevista = Calendar.getInstance();
		if(isCidadesIguais(origem.ddd, destino.ddd)) {
			entregaPrevista.add(Calendar.DATE, 1);
			totalFrete = aplicarDesconto(totalFrete, 50D);
		}
		else if(isEstadosIguais(origem.uf, destino.uf)) {
			entregaPrevista.add(Calendar.DATE, 3);
			totalFrete = aplicarDesconto(totalFrete, 75D);
		}
		else if(!isEstadosIguais(origem.uf, destino.uf)) {
			entregaPrevista.add(Calendar.DATE, 10);
		}
		
		Date dataPrevista = entregaPrevista.getTime();
		Calendar dataHoje = Calendar.getInstance();
		Frete frete = new Frete(freteRequest.getCepOrigem(), freteRequest.getCepDestino(), freteRequest.getNomeDestinatario(), totalFrete, dataPrevista, dataHoje.getTime());
		Frete saved = freteRepository.save(frete);
		FreteResponseDTO freteResponse = new FreteResponseDTO(saved);
		return freteResponse;
	}
	
	private Double aplicarDesconto(Double valor, Double porcentagem) {
		Double desconto = Utils.calcularPorcentagem(valor, porcentagem);
		return valor - desconto;
	}
	
	private Boolean isCidadesIguais(String dddOrigem, String dddDestino) {
		return dddOrigem.equalsIgnoreCase(dddDestino);
	}
	
	private Boolean isEstadosIguais(String estadoOrigem, String estadoDestino) {
		return estadoOrigem.equalsIgnoreCase(estadoDestino);
	}
}
