package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.enums.TipoLancamento;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoParcelasMultiplasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pagamento pagamento;
	private Integer quantidadeParcelas;
	private BigDecimal valorParcela;
	private Date primeiroVencimento;
	private Integer intervaloDias;
	private TipoLancamento tipoLancamento;
	
	@Inject
	private PagamentoParcelaService parcelaService;
	
	@Inject
	private MessagesHelper messagesHelper;

	public String gerarParcelas() {
//		TODO - Gerar parcelas de pagamento de acordo com formulário de parcelamento
//		parcelaService.salvar(pagamento, parcela);
		messagesHelper.addFlash(new FacesMessage("Parcelas geradas com sucesso!"));
		return "/pagamentocadastro?faces-redirect=true" + 
		"&pagamento=" + this.pagamento.getIdPagamento();
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	
	public Date getPrimeiroVencimento() {
		return primeiroVencimento;
	}

	public void setPrimeiroVencimento(Date primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}

	public Integer getIntervaloDias() {
		return intervaloDias;
	}

	public void setIntervaloDias(Integer intervaloDias) {
		this.intervaloDias = intervaloDias;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	
}
