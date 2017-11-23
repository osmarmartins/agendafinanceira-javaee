package br.com.futura.agendafinanceira.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class PagamentoCadastroBean {

	private Pagamento pagamento;
	
	private List<PagamentoParcela> parcelas;
	
	private List<Setor> setores;
	
	private List<Conta> contas;
 	
	@Inject
	private SetorDao setorDao;
	
	@Inject
	private ContaDao contaDao;
	
	@Inject
	private PagamentoDao pagamentoDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		setores = setorDao.listarTodos();
		contas = contaDao.listarTodos();
		this.pagamento = new Pagamento();
		this.parcelas = this.pagamento.getParcelas();
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
	
	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}
	
	public void setParcelas(List<PagamentoParcela> parcelas) {
		this.parcelas = parcelas;
	}
	
	public List<Setor> getSetores() {
		return setores;
	}
	
	public List<Conta> getContas() {
		return contas;
	}

}
