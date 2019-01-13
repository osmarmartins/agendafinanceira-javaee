package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Fornecedor> fornecedores;
	
	private String filtro;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@Inject
	private MessagesHelper messagesHelper;

	private List<Fornecedor> fornecedoresSelecionados;
	
	private String mensagemExclusao;
	
	private String pesquisaFiltro;
	
	@PostConstruct
	private void init(){
		this.fornecedores = fornecedorService.listarTodos();
		this.fornecedoresSelecionados = new ArrayList<>();
		this.mensagemExclusao = new String();
	}
	
	public void pesquisar(){
		if (this.pesquisaFiltro != null && !this.pesquisaFiltro.isEmpty()) {
			this.fornecedores = fornecedorService.listarPor(this.pesquisaFiltro);
		} else {
			init();
		}	}

	public void excluir(){
		fornecedorService.excluir(fornecedoresSelecionados);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void selecionaFornecedor(Fornecedor fornecedor) {
		fornecedoresSelecionados.add(fornecedor);
		mensagemExclusaoBuilder();
	}
	
	public Boolean isExisteSelecao() {
		return !fornecedoresSelecionados.isEmpty();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getFornecedoresSelecionados()!=null && !this.getFornecedoresSelecionados().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.fornecedoresSelecionados.size()>1) {
				msg.append("os setores selecionados?");
			}else {
				msg.append("o setor ");
				msg.append(fornecedoresSelecionados.get(0).getRazaoSocial());
			}
		}
		this.mensagemExclusao = msg.toString();
	}
	
	public List<Fornecedor> getFornecedoresSelecionados() {
		return fornecedoresSelecionados;
	}
	
	public void setFornecedoresSelecionados(List<Fornecedor> fornecedoresSelecionados) {
		this.fornecedoresSelecionados = fornecedoresSelecionados;
	}
	
	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
	
	public void setMensagemExclusao(String mensagemExclusao) {
		this.mensagemExclusao = mensagemExclusao;
	}
	
	public String getFiltro() {
		return filtro;
	}
	
	public void setFiltro(String pesquisa) {
		this.filtro = pesquisa;
	}
	
	public String getPesquisaFiltro() {
		return pesquisaFiltro;
	}
	
	public void setPesquisaFiltro(String pesquisaFiltro) {
		this.pesquisaFiltro = pesquisaFiltro;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

}
