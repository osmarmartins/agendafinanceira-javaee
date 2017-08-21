package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class ContaCadastroBean {

	private Conta conta;

	@Inject
	private ContaDao contaDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.conta = new Conta();
		this.conta.setAtivo(Ativo.ATIVO);
	}

	public void salvar() {
		contaDao.salvar(conta);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
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
