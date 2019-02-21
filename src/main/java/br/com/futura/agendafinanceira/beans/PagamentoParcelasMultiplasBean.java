package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.dto.ParcelamentoDto;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.enums.TipoLancamento;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoParcelasMultiplasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pagamento pagamento;

	@Inject
	private ParcelamentoDto parcelamento;

	@Inject
	private PagamentoParcelaService parcelaService;

	@Inject
	private MessagesHelper messagesHelper;

	private TipoLancamento[] tiposLancamentos;
		
	@PostConstruct
	private void init() {
		this.parcelamento = new ParcelamentoDto(this.pagamento);
		this.tiposLancamentos = TipoLancamento.values();
		
	}

	public String gerarParcelas() {
		this.parcelamento.setPagamento(pagamento);
		parcelaService.gerarParcelamento(parcelamento);
		messagesHelper.addFlash(new FacesMessage("Parcelas geradas com sucesso!"));
		return "/pagamento/pagamentocadastro?faces-redirect=true" + "&pagamento=" + this.pagamento.getIdPagamento();
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public ParcelamentoDto getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(ParcelamentoDto parcelamento) {
		this.parcelamento = parcelamento;
	}
	
	public boolean isIntervaloDias() {
		return this.parcelamento.getTipoLancamento() == TipoLancamento.INTERVALO_DIAS;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return tiposLancamentos;
	}
}
