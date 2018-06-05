package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
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
	
	@PostConstruct
	private void init(){
		this.fornecedores = fornecedorService.listarTodos();
	}
	
	public void pesquisar(){
		fornecedores = fornecedorService.listarPor(filtro);
	}

	public void excluir(Fornecedor fornecedor){
		fornecedorService.excluir(fornecedor);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public String getFiltro() {
		return filtro;
	}
	
	public void setFiltro(String pesquisa) {
		this.filtro = pesquisa;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	

}
