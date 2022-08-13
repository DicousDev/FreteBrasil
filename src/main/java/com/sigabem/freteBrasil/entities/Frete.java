package com.sigabem.freteBrasil.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_frete")
public class Frete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="\"cepOrigem\"")
	private String cepOrigem;
	
	@Column(name="\"cepDestino\"")
	private String cepDestino;
	
	@Column(name="\"nomeDestinatario\"")
	private String nomeDestinatario;
	
	@Column(name="\"vlTotalFrete\"")
	private Double vlTotalFrete;
	
	@Column(name="\"dataPrevistaEntrega\"")
	private Date dataPrevistaEntrega;
	
	@Column(name="\"dataConsulta\"")
	private Date dataConsulta;
	
	public Frete() {
		
	}

	public Frete(String cepOrigem, String cepDestino, String nomeDestinatario, Double vlTotalFrete,
			Date dataPrevistaEntrega, Date dataConsulta) {
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.nomeDestinatario = nomeDestinatario;
		this.vlTotalFrete = vlTotalFrete;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		this.dataConsulta = dataConsulta;
	}

	public Long getIdFrete() {
		return id;
	}

	public void setIdFrete(Long idFrete) {
		this.id = idFrete;
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

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public Double getVlTotalFrete() {
		return vlTotalFrete;
	}

	public void setVlTotalFrete(Double vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}

	public Date getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
}