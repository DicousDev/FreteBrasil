package com.sigabem.freteBrasil.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sigabem.freteBrasil.dto.FreteRequestDTO;
import com.sigabem.freteBrasil.dto.FreteResponseDTO;
import com.sigabem.freteBrasil.services.FreteService;

@SpringBootTest
public class FreteTest {

	@Autowired
	private FreteService freteService;
	
	@Test
	public void pedirFreteNaMesmaCidade() throws Exception {
		FreteRequestDTO frete = new FreteRequestDTO("João", "88090061", "88035111", 100D);
		FreteResponseDTO response = freteService.pedirFrete(frete);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		FreteResponseDTO result = new FreteResponseDTO(response.getIdFrete(), frete.getCepOrigem(), frete.getCepDestino(), 50D, calendar.getTime());
		Boolean dataEntregaIguais = response.getDataPrevistaEntrega().equals(result.getDataPrevistaEntrega()); 
		
		assertThat(dataEntregaIguais).isTrue();
		assertThat(response.getIdFrete()).isEqualTo(result.getIdFrete());
		assertThat(response.getCepOrigem()).isEqualTo(result.getCepOrigem());
		assertThat(response.getCepDestino()).isEqualTo(result.getCepDestino());
		assertThat(response.getVlTotalFrete()).isEqualTo(result.getVlTotalFrete());
	}
	
	@Test
	public void pedirFreteEmCidadeDiferenteNoMesmoEstado() throws Exception {
		FreteRequestDTO frete = new FreteRequestDTO("João", "88090061", "88334140", 100D);
		FreteResponseDTO response = freteService.pedirFrete(frete);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);
		FreteResponseDTO result = new FreteResponseDTO(response.getIdFrete(), frete.getCepOrigem(), frete.getCepDestino(), 25D, calendar.getTime());
		System.out.println(response.getDataPrevistaEntrega());
		System.out.println(result.getDataPrevistaEntrega());
		Boolean dataEntregaIguais = response.getDataPrevistaEntrega().equals(result.getDataPrevistaEntrega()); 
		
		assertThat(dataEntregaIguais).isTrue();
		assertThat(response.getIdFrete()).isEqualTo(result.getIdFrete());
		assertThat(response.getCepOrigem()).isEqualTo(result.getCepOrigem());
		assertThat(response.getCepDestino()).isEqualTo(result.getCepDestino());
		assertThat(response.getVlTotalFrete()).isEqualTo(result.getVlTotalFrete());
	}
	
	@Test
	public void pedirFreteParaEstadosDiferentes() throws Exception {
		FreteRequestDTO frete = new FreteRequestDTO("João", "88090061", "70740550", 100D);
		FreteResponseDTO response = freteService.pedirFrete(frete);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 10);
		FreteResponseDTO result = new FreteResponseDTO(response.getIdFrete(), frete.getCepOrigem(), frete.getCepDestino(), 100D, calendar.getTime());
		System.out.println(response.getDataPrevistaEntrega());
		System.out.println(result.getDataPrevistaEntrega());
		Boolean dataEntregaIguais = response.getDataPrevistaEntrega().equals(result.getDataPrevistaEntrega()); 
		
		assertThat(dataEntregaIguais).isTrue();
		assertThat(response.getIdFrete()).isEqualTo(result.getIdFrete());
		assertThat(response.getCepOrigem()).isEqualTo(result.getCepOrigem());
		assertThat(response.getCepDestino()).isEqualTo(result.getCepDestino());
		assertThat(response.getVlTotalFrete()).isEqualTo(result.getVlTotalFrete());
	}
	
	@Test
	public void pedirFreteComCEPInvalido() {
		FreteRequestDTO frete = new FreteRequestDTO("João", "12345678", "70740550", 100D);
		
		Assertions.assertThrows(Exception.class, new Executable() {
			
			public void execute() throws Exception {
				freteService.pedirFrete(frete);
			}
		});
	}
	
	@Test
	public void pedirFreteSemCEP() {
		FreteRequestDTO frete = new FreteRequestDTO("João", "1", "70740550", 100D);
		
		Assertions.assertThrows(Exception.class, new Executable() {
			
			public void execute() throws Exception {
				freteService.pedirFrete(frete);
			}
		});
	}
	
	@Test
	public void pedirFreteComCEPMalFormatado() {
		FreteRequestDTO frete = new FreteRequestDTO("João", "88090-061", "70740-550", 100D);
		assertThat(frete.getCepOrigem()).isEqualTo("88090061");
		assertThat(frete.getCepDestino()).isEqualTo("70740550");
	}
	
	@Test
	public void aplicar50porcentoDeDescontoNoFrete() {
		
		Double frete = 500D;
		Double freteComDesconto = freteService.aplicarDesconto(frete, 50D);
		assertThat(freteComDesconto).isEqualTo(250D);
	}
	
	@Test
	public void aplicar75porcentoDeDescontoNoFrete() {
		
		Double frete = 500D;
		Double freteComDesconto = freteService.aplicarDesconto(frete, 75D);
		assertThat(freteComDesconto).isEqualTo(125D);
	}
	
	@Test
	public void aplicar100porcentoDeDescontoNoFrete() {
		
		Double frete = 500D;
		Double freteComDesconto = freteService.aplicarDesconto(frete, 100D);
		assertThat(freteComDesconto).isEqualTo(0D);
	}
	
	@Test
	public void aplicarDescontoNegativo() {
		Double frete = 500D;
		Assertions.assertThrows(RuntimeException.class, new Executable() {
			
			public void execute() {
				freteService.aplicarDesconto(frete, -50D);
			}
		});
	}
	
	@Test
	public void isCidadesIguais() {
		String origem = "48";
		String destino = "48";
		Boolean cidadesIguais = freteService.isCidadesIguais(origem, destino);
		assertThat(cidadesIguais).isTrue();
	}
	
	@Test
	public void isCidadesDiferentes() {
		String origem = "48";
		String destino = "49";
		Boolean cidadesIguais = freteService.isCidadesIguais(origem, destino);
		assertThat(cidadesIguais).isFalse();
	}
	
	@Test
	public void isEstadosIguais() {
		String origem = "SC";
		String destino = "SC";
		Boolean estadosIguais = freteService.isEstadosIguais(origem, destino);
		assertThat(estadosIguais).isTrue();
	}
	
	@Test
	public void isEstadosDiferentes() {
		String origem = "SC";
		String destino = "RS";
		Boolean estadosIguais = freteService.isEstadosIguais(origem, destino);
		assertThat(estadosIguais).isFalse();
	}
}
