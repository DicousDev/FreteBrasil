package com.sigabem.freteBrasil.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sigabem.freteBrasil.utils.Utils;

public class FreteRequestDTO {

	@NotNull(message = "Preencha o nome do destinatario!")
	@NotBlank(message = "Preencha o nome do destinatario!")
	private String nomeDestinatario;
	
	@NotNull(message = "Preencha o cep da origem!")
	@NotBlank(message = "Preencha o cep da origem!")
	private String cepOrigem;
	
	@NotNull(message = "Preencha o cep do destino!")
	@NotBlank(message = "Preencha o cep do destino!")
	private String cepDestino;
	
	@NotNull
	@Min(value = 0, message = "O peso tem que ser igual ou maior que 0.")
	private Double peso;
	
	public FreteRequestDTO() {
		
	}
	
	public FreteRequestDTO(String nomeDestinatario, String cepOrigem, String cepDestino, Double peso) {
		this.nomeDestinatario = nomeDestinatario;
		setCepOrigem(cepOrigem);
		setCepDestino(cepDestino);
		this.peso = peso;
	}
	
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public Double getPeso() {
		return peso;
	}
	
	private void setCepOrigem(String cep) {
		
		cepOrigem = Utils.apenasNumero(cep);
		if(cepOrigem.length() >= 8) {
			cepOrigem = cepOrigem.substring(0, 8);
		}
	}
	
	private void setCepDestino(String cep) {
		cepDestino = Utils.apenasNumero(cep);
		if(cepDestino.length() >= 8) {
			cepDestino = cepDestino.substring(0, 8);
		}
	}
}
