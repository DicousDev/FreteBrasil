package com.sigabem.freteBrasil.dto;

public class FreteRequestDTO {

	private String nomeDestinatario;
	private String cepOrigem;
	private String cepDestino;
	private Double peso;
	
	public FreteRequestDTO() {
		
	}
	
	public FreteRequestDTO(String nomeDestinatario, String cepOrigem, String cepDestino, Double peso) {
		this.nomeDestinatario = nomeDestinatario;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.peso = peso;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
}
