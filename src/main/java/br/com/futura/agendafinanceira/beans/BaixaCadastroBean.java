package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

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
	
	public FormaPagamento[] getFormasDePagamento() {
		return FormaPagamento.values();
	}
	
	public String salvar() {
		baixaParcelaService.salvar(parcela, quitacao);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/baixa?faces-redirect=true";
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
		return quitacao;
	}
	public void setQuitacao(PagamentoQuitacao quitacao) {
		this.quitacao = quitacao;
	}
	
	
	
	

}
