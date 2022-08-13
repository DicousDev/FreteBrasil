package com.sigabem.freteBrasil.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sigabem.freteBrasil.entities.Frete;

public class FreteResponseDTO {

	private Long idFrete;
	private String cepOrigem;
	private String cepDestino;
	private Double vlTotalFrete;
	private String dataPrevistaEntrega;
	
	public FreteResponseDTO() {
		
	}
	
	public FreteResponseDTO(Long idFrete, String cepOrigem, String cepDestino, Double valorTotalFrete, Date dataPrevistaEntrega) {
		this.idFrete = idFrete;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		vlTotalFrete = valorTotalFrete;
		setDataPrevistaEntrega(dataPrevistaEntrega);
	}
	
	public FreteResponseDTO(Frete frete) {
		this.idFrete = frete.getIdFrete();
		this.cepOrigem = frete.getCepOrigem();
		this.cepDestino = frete.getCepDestino();
		vlTotalFrete = frete.getVlTotalFrete();
		setDataPrevistaEntrega(frete.getDataPrevistaEntrega());
	}
	
	public Long getIdFrete() {
		return idFrete;
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
