package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.services.ContaService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class ContaCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Conta conta;

	@Inject
	private ContaService contaService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.conta = new Conta();
		this.conta.setAtivo(Ativo.ATIVO);
	}

	public String salvar() {
		contaService.salvar(conta);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
		return "contacadastro?faces-redirect=true";
	}

	public Conta getConta() {
		if (this.conta == null) {
			init();
		}
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
