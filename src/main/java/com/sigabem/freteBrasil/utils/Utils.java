package com.sigabem.freteBrasil.utils;

public class Utils {

	public static String apenasNumero(String texto) {
		return texto.replaceAll("[^0-9]", "");
	}
	
	public static Double calcularPorcentagem(Double valor, Double porcentagem) {
		return (porcentagem / 100) * valor;
	}
}
