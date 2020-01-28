package br.com.futura.agendafinanceira.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentoDto {

	private Integer parcela;
	private Date vencimento;
	private String fornecedor;
	private String historico;
	private BigDecimal saldoDevedor;

	public PagamentoDto(Integer parcela, Date vencimento, String fornecedor, String historico,
			BigDecimal saldoDevedor) {
		super();
		this.parcela = parcela;
		this.vencimento = vencimento;
		this.fornecedor = fornecedor;
		this.historico = historico;
		this.saldoDevedor = saldoDevedor;
	}

	public Integer getParcela() {
		return parcela;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getHistorico() {
		return historico;
	}

	public BigDecimal getSaldoDevedor() {
		return saldoDevedor;
	}

	@Override
	public String toString() {
		return "PagamentoDto [parcela=" + parcela + ", vencimento=" + vencimento + ", fornecedor=" + fornecedor
				+ ", historico=" + historico + ", saldoDevedor=" + saldoDevedor + "]";
	}

}
