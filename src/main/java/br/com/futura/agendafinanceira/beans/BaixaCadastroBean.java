package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.models.enums.FormaPagamento;
import br.com.futura.agendafinanceira.services.BaixaParcelaService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class BaixaCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PagamentoParcela parcela;
	private PagamentoQuitacao quitacao;
	
	@Inject
	private BaixaParcelaService baixaParcelaService;
	
	@Inject
	private MessagesHelper messagesHelper;
	
	@PostConstruct
	private void init() {
		quitacao = new PagamentoQuitacao();
		quitacao.setDtPgto(new Date());
	}
	
	
	public FormaPagamento[] getFormasDePagamento() {
		return FormaPagamento.values();
	}
	
	public String salvar() {
		baixaParcelaService.salvar(parcela, quitacao);
		Float novoSaldoDevedor = parcela.saldoDevedor().subtract(quitacao.getValor()).floatValue();
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		
		if (novoSaldoDevedor == 0 ) {
			return "baixa?faces-redirect=true";
		}

		return "baixacadastro?faces-redirect=true&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	public String excluir(PagamentoQuitacao quitacao) {
		// TODO Chamada Ajax para atualizar grid de quitações após excluir
		parcela.removeQuitacao(quitacao);
		quitacao.setParcela(parcela);
		
		baixaParcelaService.excluir(quitacao);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "baixacadastro?faces-redirect=true&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	public void alterar(PagamentoQuitacao quitacao) {
		this.quitacao = quitacao;
	}
	
	public boolean habilitarQuitacoes() {
		return this.parcela.getQuitacoes().size() > 0;
	}
	
	public PagamentoParcela getParcela() {
		if (this.parcela == null) {
			this.parcela = new PagamentoParcela();
		}
		return parcela;
	}
	
	public void setParcela(PagamentoParcela parcela) {
		this.parcela = parcela;
	}
	
	public PagamentoQuitacao getQuitacao() {
		if (this.quitacao == null) {
			this.quitacao = new PagamentoQuitacao();
		}
		this.quitacao.setParcela(parcela);
		this.quitacao.setValor(parcela.saldoDevedor());
		return quitacao;
	}
	
	public void setQuitacao(PagamentoQuitacao quitacao) {
		this.quitacao = quitacao;
	}
	
	public List<PagamentoQuitacao> getQuitacoes() {
		return this.parcela.getQuitacoes();
	}

}
