package com.sigabem.freteBrasil.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FreteResponseDTO {

	private String cepOrigem;
	private String cepDestino;
	private Double vlTotalFrete;
	private String dataPrevistaEntrega;
	
	public FreteResponseDTO() {
		
	}
	
	public FreteResponseDTO(String cepOrigem, String cepDestino, Double valorTotalFrete, Date dataPrevistaEntrega) {
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		vlTotalFrete = valorTotalFrete;
		setDataPrevistaEntrega(dataPrevistaEntrega);
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

	public Double getVlTotalFrete() {
		return vlTotalFrete;
	}

	public void setVlTotalFrete(Double vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}

	public String getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(Date dataPrevista) {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		dataPrevistaEntrega = simple.format(dataPrevista);
	}
}
