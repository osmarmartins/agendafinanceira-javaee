package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.SituacaoPagamento;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.services.PagamentoService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoCadastroBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pagamento pagamento;
	
	private List<PagamentoParcela> parcelas;
	
	private List<Setor> setores;
	
	private List<Conta> contas;
	
	private List<Fornecedor> fornecedores;
 	
	@Inject
	private SetorDao setorDao;
	
	@Inject
	private ContaDao contaDao;
	
	@Inject
	private FornecedorDao fornecedorDao;
	
	@Inject
	private PagamentoService pagamentoService;
	
	@Inject
	private PagamentoParcelaService parcelaService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.setores = setorDao.listarTodos();
		this.contas = contaDao.listarTodos();
		this.fornecedores = fornecedorDao.listarTodos();
		this.pagamento = new Pagamento();
		this.pagamento.setEmissao(new Date());
		this.pagamento.setSituacao(SituacaoPagamento.EMABERTO);
	}
	
	public String salvar() {
		pagamentoService.salvar(pagamento);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public String alterar(PagamentoParcela parcela) {
		return "/pagamentocadastroparcela?faces-redirect=true" +
					"&pagamento=" + parcela.getPagamento().getIdPagamento() + 
					"&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	public String excluir(PagamentoParcela parcela){
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		parcelaService.excluir(parcela);
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public String novaParcela() {
		return "/pagamentocadastroparcela?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public boolean habilitarParcelas() {
		return this.pagamento.getIdPagamento() != null;
	}
	
	public Pagamento getPagamento() {
		if (this.pagamento == null){
			this.pagamento = new Pagamento();
		}

		if (this.pagamento.getParcelas()!=null) {
			pagamentoService.calcularTotais(this.pagamento);
		}
		
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}
	
	public List<Setor> getSetores() {
		return setores;
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

}
