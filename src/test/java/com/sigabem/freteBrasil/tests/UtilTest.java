package com.sigabem.freteBrasil.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sigabem.freteBrasil.utils.Utils;


@SpringBootTest
public class UtilTest {

	@Test
	public void apenasNumero() {
		String texto = "teste--123";
		texto = Utils.apenasNumero(texto);
		assertThat(texto).isEqualTo("123");
	}
	
	@Test
	public void calcularPorcentagem50() {
		Double porcentagem = 50D;
		Double valor = 500D;
		Double resultado = Utils.calcularPorcentagem(valor, porcentagem);
		assertThat(resultado).isEqualTo(250D);
	}
	
	@Test
	public void calcularPorcentagem75() {
		Double porcentagem = 75D;
		Double valor = 500D;
		Double resultado = Utils.calcularPorcentagem(valor, porcentagem);
		assertThat(resultado).isEqualTo(375D);
	}
	
	@Test
	public void calcularPorcentagem100() {
		Double porcentagem = 100D;
		Double valor = 500D;
		Double resultado = Utils.calcularPorcentagem(valor, porcentagem);
		assertThat(resultado).isEqualTo(500D);
	}
}
