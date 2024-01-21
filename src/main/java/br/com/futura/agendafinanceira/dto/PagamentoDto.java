package br.com.futura.agendafinanceira.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentoDto {

	private Integer pagamento;
	private Integer idParcela;
	private String parcela;
	private Date vencimento;
	private String fornecedor;
	private String historico;
	private BigDecimal valor;
	private BigDecimal saldoDevedor;

	public PagamentoDto(Integer pagamento, Integer idParcela, String parcela, Date vencimento, String fornecedor,
			String historico, BigDecimal valor, BigDecimal saldoDevedor) {
		this.pagamento = pagamento;
		this.idParcela = idParcela;
		this.parcela = parcela;
		this.vencimento = vencimento;
		this.fornecedor = fornecedor;
		this.historico = historico;
		this.valor = valor;
		this.saldoDevedor = saldoDevedor;
	}

	public Integer getPagamento() {
		return pagamento;
	}

	public Integer getIdParcela() {
		return idParcela;
	}

	public String getParcela() {
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

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getSaldoDevedor() {
		return saldoDevedor;
	}

}
