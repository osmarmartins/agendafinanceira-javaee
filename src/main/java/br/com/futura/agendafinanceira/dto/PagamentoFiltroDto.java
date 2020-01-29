package br.com.futura.agendafinanceira.dto;

import java.util.Date;

import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

public class PagamentoFiltroDto {

	private Fornecedor fornecedor;
	private String historico;
	private SituacaoParcela situacao;
	private Date dataInicial;
	private Date dataFinal;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public SituacaoParcela getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoParcela situacao) {
		this.situacao = situacao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	@Override
	public String toString() {
		return "PagamentoFiltroDto [fornecedor=" + fornecedor + ", historico=" + historico + ", situacao=" + situacao
				+ ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + "]";
	}

}
