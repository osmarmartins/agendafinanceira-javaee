package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoParcelaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Pagamento pagamento;
	private PagamentoParcela parcela;
	
	@Inject
	private PagamentoParcelaService parcelaService;
	
	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.parcela = new PagamentoParcela(pagamento);
	}
	
	public String salvar() {
		parcelaService.salvar(parcela);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamentocadastro?faces-redirect=true" + 
				"&pagamento=" + parcela.getPagamento().getIdPagamento();
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public PagamentoParcela getParcela() {
		if (parcela == null) {
			init();
		}
		parcela.setPagamento(pagamento);
		return parcela;
	}

	public void setParcela(PagamentoParcela parcela) {
		this.parcela = parcela;
	}
	
	

}