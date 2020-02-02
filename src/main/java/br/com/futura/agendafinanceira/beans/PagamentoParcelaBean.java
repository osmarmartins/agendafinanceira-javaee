package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

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
	private Date dataLiquidacao;

	@Inject
	private PagamentoParcelaService parcelaService;
	
	@Inject
	private MessagesHelper messagesHelper;

	public String salvar() {
		parcelaService.salvar(pagamento, parcela);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamento/pagamentocadastro?faces-redirect=true" + 
				"&pagamento=" + parcela.getPagamento().getIdPagamento();
	}
	
	public String excluir(PagamentoParcela parcela){
		String pagamento = parcela.getPagamento().getIdPagamento().toString();
		parcelaService.excluir(parcela);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamento/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento;
	}
	
	public String liquidar() {
		this.parcela = parcelaService.liquidar(this.parcela);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		return "/pagamento/pagamentocadastro?faces-redirect=true" + 
				"&pagamento=" + parcela.getPagamento().getIdPagamento();
	}
	
	public boolean isPermiteAlterar() {
		PagamentoParcela p = getParcela();
		return p.getSituacao() != SituacaoParcela.LIQUIDADO &&
			p.getSituacao() != SituacaoParcela.BAIXADO &&
			p.getSituacao() != SituacaoParcela.CANCELADO;
	}
	
	public void definirPagamentoAVista() {
		this.dataLiquidacao = this.parcela.getVencimento();
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public PagamentoParcela getParcela() {
		if (parcela == null) {
			parcela = new PagamentoParcela(
					"1/1",
					SituacaoParcela.NOVO,
					Calendar.getInstance().getTime(),
					Collections.emptySet(),
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					BigDecimal.ZERO
					);
		}
		return parcela;
	}

	public void setParcela(PagamentoParcela parcela) {
		this.parcela = parcela;
	}
	
	public Date getDataLiquidacao() {
		if (this.dataLiquidacao == null) {
			this.dataLiquidacao = new Date();
		}
		return dataLiquidacao;
	}
	
	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}
	
}
