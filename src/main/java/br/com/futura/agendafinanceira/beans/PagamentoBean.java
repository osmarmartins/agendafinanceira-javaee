package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Pagamento> pagamentos;

	private String pesquisa;

	@Inject
	private PagamentoDao pagamentoDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.pagamentos = pagamentoDao.listarTodos(); 
	}

	public void pesquisar() {
		this.pagamentos = pagamentoDao.listarPor(this.pesquisa);
	}

	public String alterar(Pagamento pagamento) {
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}

	public void excluir(Pagamento pagamento) {
		pagamentoDao.excluir(pagamento);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
}
