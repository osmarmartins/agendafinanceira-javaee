package br.com.futura.agendafinanceira.dto;

import java.math.BigDecimal;

public class DespesaMesDto {

	private Integer ano;
	private Integer mes;
	private BigDecimal valor;
	private String referencia;
	private String[] meses = { "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ" };

	public DespesaMesDto(Integer ano, Integer mes, BigDecimal valor) {
		this.ano = ano;
		this.mes = mes;
		this.valor = valor;
	}

	public Integer getAno() {
		return ano;
	}

	public Integer getMes() {
		return mes;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getReferencia() {
		String mes = meses[this.mes - 1] + "/" + this.ano;
		return mes;
	}

	@Override
	public String toString() {
		return "DespesaMesDto [ano=" + ano + ", mes=" + mes + ", valor=" + valor + ", referencia=" + referencia + "]";
	}

}
