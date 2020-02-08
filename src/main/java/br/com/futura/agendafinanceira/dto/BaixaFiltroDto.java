package br.com.futura.agendafinanceira.dto;

import java.util.Date;

import br.com.futura.agendafinanceira.models.Fornecedor;

public class BaixaFiltroDto {

	private Date dataInicial;
	private Date dataFinal;
	private Fornecedor fornecedor;
	private String historico;
	
	private PaginacaoDto paginacao = new PaginacaoDto();

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
	
	public String getHistorico() {
		return historico;
	}
	
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
	public PaginacaoDto getPaginacao() {
		return paginacao;
	}
	
	public void setPaginacao(PaginacaoDto paginacao) {
		this.paginacao = paginacao;
	}

	@Override
	public String toString() {
		return "BaixaFiltroDto [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", fornecedor=" + fornecedor
				+ ", historico=" + historico + "]";
	}

}
