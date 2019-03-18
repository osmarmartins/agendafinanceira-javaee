package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.services.PagamentoService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Pagamento> pagamentos;

	private String pesquisaFiltro;

	@Inject
	private PagamentoService pagamentoService;

	@Inject
	private MessagesHelper messagesHelper;
	
	private List<Pagamento> pagamentosSelecionados;
	
	private String mensagemExclusao;

	@PostConstruct
	private void init() {
		this.pagamentos = pagamentoService.listarTodos();
		this.pagamentosSelecionados = new ArrayList<>();
		this.mensagemExclusao = new String();
	}

	public void pesquisar() {
		this.pagamentos = pagamentoService.listarPor(this.pesquisaFiltro);
	}

	public String alterar(Pagamento pagamento) {
		return "/pagamento/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	
	public Boolean isExisteSelecao() {
		return !pagamentosSelecionados.isEmpty();
	}
	
	public void selecionaPagamento(Pagamento pagamento) {
		pagamentosSelecionados.add(pagamento);
		mensagemExclusaoBuilder();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getPagamentosSelecionados()!=null && !this.getPagamentosSelecionados().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.getPagamentosSelecionados().size()>1) {
				msg.append("os pagamentos selecionados?");
			}else {
				msg.append("o pagamento ");
				msg.append(pagamentosSelecionados.get(0).getHistorico());
			}
		}
		this.mensagemExclusao = msg.toString();
	}

	public void excluir() {
		pagamentoService.excluir(pagamentosSelecionados);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
	public void setPesquisaFiltro(String pesquisaFiltro) {
		this.pesquisaFiltro = pesquisaFiltro;
	}
	
	public String getPesquisaFiltro() {
		return pesquisaFiltro;
	}

	public List<Pagamento> getPagamentosSelecionados() {
		return pagamentosSelecionados;
	}

	public void setPagamentosSelecionados(List<Pagamento> pagamentosSelecionados) {
		this.pagamentosSelecionados = pagamentosSelecionados;
	}

	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
	
}
