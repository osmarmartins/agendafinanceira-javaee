package br.com.futura.agendafinanceira.dto;

import java.util.Date;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.utils.DataUtil;

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
	
	public String periodo() {
		return DataUtil.dataDMA(this.dataInicial) + " - " + DataUtil.dataDMA(this.dataFinal);
	}
	
	public String selecao() {
		StringBuilder selecao = new StringBuilder();
		
		if (this.setor != null) {
			selecao.append("   Setor: " + this.setor.getDescricao());
		}
		
		if (this.conta != null) {
			selecao.append("   Conta: " + this.conta.getDescricao());
		}
		
		if (this.fornecedor != null) {
			selecao.append("   Fornecedor: " + this.fornecedor.getRazaoSocial());
		}
		
		if (selecao.toString().trim().isEmpty()) {
			selecao.append("Nenhum");
		}
		
		return selecao.toString().trim();
	}

	@Override
	public String toString() {
		return "RelatorioContasAPagarDto [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", setor=" + setor
				+ ", conta=" + conta + ", fornecedor=" + fornecedor + "]";
	}

}
