package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class PagamentoCadastroBean {

	private Pagamento pagamento;
	
	@Inject
	private PagamentoDao pagamentoDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.pagamento = new Pagamento();
	}

	public void salvar() {
		pagamentoDao.salvar(pagamento);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}
