package br.com.futura.agendafinanceira.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.utils.DataUtil;

public class RelatorioFiltroDto {

	private Date dataInicial;
	private Date dataFinal;

	// Arrays (e não List) para o JSF descobrir o conversor de cada item na seleção múltipla
	private Setor[] setores;
	private Conta[] contas;
	private Fornecedor[] fornecedores;

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

	public Setor[] getSetores() {
		return setores;
	}

	public void setSetores(Setor[] setores) {
		this.setores = setores;
	}

	public Conta[] getContas() {
		return contas;
	}

	public void setContas(Conta[] contas) {
		this.contas = contas;
	}

	public Fornecedor[] getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedor[] fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Setor> listaSetores() {
		return paraLista(this.setores);
	}

	public List<Conta> listaContas() {
		return paraLista(this.contas);
	}

	public List<Fornecedor> listaFornecedores() {
		return paraLista(this.fornecedores);
	}

	private static <T> List<T> paraLista(T[] itens) {
		if (itens == null) {
			return Collections.emptyList();
		}
		return Arrays.asList(itens);
	}

	// Evita que quebras de linha ou espaços extras do cadastro desperdicem as 3 linhas do filtro no relatório
	private static String normalizar(String texto) {
		return texto == null ? "" : texto.replaceAll("\\s+", " ").trim();
	}

	public String periodo() {
		return DataUtil.dataDMA(this.dataInicial) + " - " + DataUtil.dataDMA(this.dataFinal);
	}

	public String selecao() {
		StringBuilder selecao = new StringBuilder();

		if (!listaSetores().isEmpty()) {
			selecao.append("   Setor: " + listaSetores().stream()
					.map(Setor::getDescricao).map(RelatorioFiltroDto::normalizar).collect(Collectors.joining(", ")));
		}

		if (!listaContas().isEmpty()) {
			selecao.append("   Conta: " + listaContas().stream()
					.map(Conta::getDescricao).map(RelatorioFiltroDto::normalizar).collect(Collectors.joining(", ")));
		}

		if (!listaFornecedores().isEmpty()) {
			selecao.append("   Fornecedor: " + listaFornecedores().stream()
					.map(Fornecedor::getRazaoSocial).map(RelatorioFiltroDto::normalizar).collect(Collectors.joining(", ")));
		}

		if (selecao.toString().trim().isEmpty()) {
			selecao.append("Nenhum");
		}

		return selecao.toString().trim();
	}

	@Override
	public String toString() {
		return "RelatorioContasAPagarDto [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal
				+ ", setores=" + Arrays.toString(setores) + ", contas=" + Arrays.toString(contas)
				+ ", fornecedores=" + Arrays.toString(fornecedores) + "]";
	}

}
