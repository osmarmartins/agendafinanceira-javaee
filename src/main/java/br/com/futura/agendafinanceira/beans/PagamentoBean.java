package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PagamentoParcela> parcelas;

	private String pesquisa;
	private Date vencimentoInicial;
	private Date vencimentoFinal;
	private SituacaoParcela situcaoParcela;

	@Inject
	private PagamentoDao pagamentoDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.parcelas = pagamentoDao.listarTodos();
	}

	public void pesquisar() {
		this.parcelas = pagamentoDao.listarPor(this.pesquisa);
	}

	public String alterar(PagamentoParcela parcela) {
		return "/pagamentocadastro?faces-redirect=true&pagamento=" + parcela.getPagamento().getIdPagamento();
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

	public Date getVencimentoInicial() {
		return vencimentoInicial;
	}

	public void setVencimentoInicial(Date vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
	}

	public Date getVencimentoFinal() {
		return vencimentoFinal;
	}

	public void setVencimentoFinal(Date vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}

	public SituacaoParcela getSitucaoParcela() {
		return situcaoParcela;
	}

	public void setSitucaoParcela(SituacaoParcela situcaoParcela) {
		this.situcaoParcela = situcaoParcela;
	}

	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<PagamentoParcela> parcelas) {
		this.parcelas = parcelas;
	}

}
