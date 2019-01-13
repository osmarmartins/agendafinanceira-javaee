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

	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private ContaService contaService;

	private List<Conta> contasSelecionadas;

	private String mensagemExclusao;

	private String pesquisaFiltro;

	@PostConstruct
	private void init() {
		this.contas = contaService.listarTodos();
		this.contasSelecionadas = new ArrayList<>();
		this.mensagemExclusao = new String();
		this.pesquisaFiltro = new String();
	}

	public void pesquisar() {
		if (this.pesquisaFiltro != null && !this.pesquisaFiltro.isEmpty()) {
			this.contas = contaService.listarPorDescricao(this.pesquisaFiltro);
		}
	}

	public void excluir() {
		contaService.excluir(contasSelecionadas);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void setPesquisaDescricao(String pesquisaDescricao) {
		this.pesquisaFiltro = pesquisaDescricao;
	}

	public String getPesquisaDescricao() {
		return pesquisaFiltro;
	}

	public List<Conta> getContas() {
		return contas;
	}

	
	public void selecionaConta(Conta conta) {
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
	
	public String getPesquisaFiltro() {
		return pesquisaFiltro;
	}
	
	public void setPesquisaFiltro(String pesquisaFiltro) {
		this.pesquisaFiltro = pesquisaFiltro;
	}
}
