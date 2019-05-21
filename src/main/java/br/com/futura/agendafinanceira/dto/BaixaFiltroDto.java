package br.com.futura.agendafinanceira.dto;

import java.util.Date;

import br.com.futura.agendafinanceira.models.Fornecedor;

public class BaixaFiltroDto {

	private Date dataInicial;
	private Date dataFinal;
	private Fornecedor fornecedor;

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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "BaixaFiltroDto [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", fornecedor=" + fornecedor
				+ "]";
	}
	
}
