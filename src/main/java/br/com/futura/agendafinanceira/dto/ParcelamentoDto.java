package br.com.futura.agendafinanceira.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.enums.TipoLancamento;

public class ParcelamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pagamento pagamento;
	private Integer quantidadeParcelas;
	private BigDecimal valorParcela;
	private Date primeiroVencimento;
	private Integer intervaloDias;
	private TipoLancamento tipoLancamento;

	public ParcelamentoDto(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public ParcelamentoDto() {
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getPrimeiroVencimento() {
		return primeiroVencimento;
	}

	public void setPrimeiroVencimento(Date primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}

	public Integer getIntervaloDias() {
		return intervaloDias;
	}

	public void setIntervaloDias(Integer intervaloDias) {
		this.intervaloDias = intervaloDias;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	@Override
	public String toString() {
		return "ParcelamentoDto [pagamento=" + pagamento + ", quantidadeParcelas=" + quantidadeParcelas
				+ ", valorParcela=" + valorParcela + ", primeiroVencimento=" + primeiroVencimento + ", intervaloDias="
				+ intervaloDias + ", tipoLancamento=" + tipoLancamento + "]";
	}

}
