package br.com.futura.agendafinanceira.dto;

import java.util.Date;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Setor;

public class RelatorioFiltroDto {

	private Date dataInicial;
	private Date dataFinal;
	private Setor setor;
	private Conta conta;
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

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "RelatorioContasAPagarDto [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", setor=" + setor
				+ ", conta=" + conta + ", fornecedor=" + fornecedor + "]";
	}

}
