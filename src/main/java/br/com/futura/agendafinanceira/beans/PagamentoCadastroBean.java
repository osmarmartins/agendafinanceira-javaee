package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
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
	private PagamentoDao pagamentoDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		setores = setorDao.listarTodos();
		contas = contaDao.listarTodos();
		fornecedores = fornecedorDao.listarTodos();
		this.pagamento = new Pagamento();
		this.pagamento.setEmissao(new Date());
		this.pagamento.setSituacao(SituacaoPagamento.EMABERTO);
	}
	
	public String salvar() {
		pagamentoDao.salvar(pagamento);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public void excluirParcela(PagamentoParcela parcela){
		System.out.println(">>>>> excluir parcela" + parcela.toString());
	}
	
	public Pagamento getPagamento() {
		if (this.pagamento == null){
			init();
		}
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
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

}
