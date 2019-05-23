package br.com.futura.agendafinanceira.dto;

import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

public class PagamentoFiltroDto {

	private Fornecedor fornecedor;
	private String historico;
	private SituacaoParcela situacao;

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

	@Override
	public String toString() {
		return "PagamentoFiltroDto [fornecedor=" + fornecedor + ", historico=" + historico + ", situacao=" + situacao
				+ "]";
	}

}
