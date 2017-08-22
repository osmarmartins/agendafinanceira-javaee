package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class ContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Conta> contas = new ArrayList<Conta>();

	private String pesquisaDescricao;

	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private ContaDao contaDao;

	@PostConstruct
	private void init() {
		this.contas = contaDao.listarTodos();
		this.pesquisaDescricao = new String();
	}

	public void pesquisar() {
		if (this.pesquisaDescricao != null && !this.pesquisaDescricao.isEmpty()) {
			this.contas = contaDao.listarPorDescricao(this.pesquisaDescricao);
		}
	}

	public String alterar(Conta conta) {
		return "/contacadastro?faces-redirect=true&conta=" + conta.getIdConta();
	}

	public void excluir(Conta conta) {
		contaDao.excluir(conta);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void setPesquisaDescricao(String pesquisaDescricao) {
		this.pesquisaDescricao = pesquisaDescricao;
	}

	public String getPesquisaDescricao() {
		return pesquisaDescricao;
	}

	public List<Conta> getContas() {
		return contas;
	}

}
