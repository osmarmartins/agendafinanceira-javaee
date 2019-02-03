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

	@PostConstruct
	private void init() {
		this.parcelamento = new ParcelamentoDto();
		this.parcelamento.setTipoLancamento(TipoLancamento.MENSAL);
	}

	public String gerarParcelas() {
		this.parcelamento.setPagamento(pagamento);

		// TODO - Gerar parcelas de pagamento de acordo com formulário de parcelamento
		// parcelaService.salvar(pagamento, parcela);

		System.out.println(">>>>>>>> GERACAO DAS PARCELAS " + this.parcelamento);

		messagesHelper.addFlash(new FacesMessage("Parcelas geradas com sucesso!" + this.parcelamento));
		return "/pagamento/pagamentocadastro?faces-redirect=true" + "&pagamento=" + this.pagamento.getIdPagamento();
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public ParcelamentoDto getParcelamento() {
//		if (this.parcelamento == null) {
//			this.parcelamento = new ParcelamentoDto(this.pagamento);
//		}
		return this.parcelamento;
	}

	public void setParcelamento(ParcelamentoDto parcelamento) {
		this.parcelamento = parcelamento;
	}

}
