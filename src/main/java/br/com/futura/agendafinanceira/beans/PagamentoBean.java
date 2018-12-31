package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
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

	private String filtro;

	@Inject
	private PagamentoService pagamentoService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.pagamentos = pagamentoService.listarTodos(); 
	}

	public void pesquisar() {
		this.pagamentos = pagamentoService.listarPor(this.filtro);
	}

	public String alterar(Pagamento pagamento) {
		return "/pagamento/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}

	public void excluir(Pagamento pagamento) {
		// TODO Exclusão de pagamentos: Validar dependências
		pagamentoService.excluir(pagamento);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String pesquisa) {
		this.filtro = pesquisa;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
}
