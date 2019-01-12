package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.services.ContaService;
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
	private ContaService contaService;

	private List<Conta> contasSelecionadas;

	private String mensagemExclusao;

	@PostConstruct
	private void init() {
		this.contas = contaService.listarTodos();
		this.pesquisaDescricao = new String();
		this.contasSelecionadas = new ArrayList<>();
		this.mensagemExclusao = new String();
	}

	public void pesquisar() {
		if (this.pesquisaDescricao != null && !this.pesquisaDescricao.isEmpty()) {
			this.contas = contaService.listarPorDescricao(this.pesquisaDescricao);
		}
	}

	public void excluir() {
		contaService.excluir(contasSelecionadas);
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

	
	public void selecionaConta(Conta conta) {
		contasSelecionadas = new ArrayList<>();
		contasSelecionadas.add(conta);
		mensagemExclusaoBuilder();
	}
	
	public Boolean isExisteSelecao() {
		return !contasSelecionadas.isEmpty();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getContasSelecionadas()!=null && !this.getContasSelecionadas().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.contasSelecionadas.size()>1) {
				msg.append("as contas selecionadas?");
			}else {
				msg.append("a conta ");
				msg.append(contasSelecionadas.get(0).getDescricao());
			}
		}
		this.mensagemExclusao = msg.toString();
	}

	public List<Conta> getContasSelecionadas() {
		return contasSelecionadas;
	}
	
	public void setContasSelecionadas(List<Conta> contasSelecionadas) {
		this.contasSelecionadas = contasSelecionadas;
	}
	
	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
	
}
