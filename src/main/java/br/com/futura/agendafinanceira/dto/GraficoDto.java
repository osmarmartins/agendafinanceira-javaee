package br.com.futura.agendafinanceira.dto;

import java.math.BigDecimal;

public class GraficoDto {

	private String legenda;
	private BigDecimal valor;
	
	public GraficoDto(String legenda, BigDecimal valor) {
		this.legenda = legenda;
		this.valor = valor;
	}

	public String getLegenda() {
		return legenda;
	}

	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "GraficoDto [legenda=" + legenda + ", valor=" + valor + "]";
	}

}
