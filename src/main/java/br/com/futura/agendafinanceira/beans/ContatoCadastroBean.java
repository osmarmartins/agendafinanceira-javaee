package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.models.Contato;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.services.ContatoService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class ContatoCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;

	private Contato contato;
	
	@Inject
	private ContatoService contatoService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.contato = new Contato();
		this.contato.setAtivo(Ativo.ATIVO);
	}

	public String salvar() {
		contatoService.salvar(contato);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso."));
		return "fornecedorcadastro?faces-redirect=true&fornecedor=" + contato.getFornecedor().getIdFornecedor(); 
	}

	public Contato getContato() {
		if (contato == null) {
			init();
		}
		this.contato.setFornecedor(this.fornecedor);
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
