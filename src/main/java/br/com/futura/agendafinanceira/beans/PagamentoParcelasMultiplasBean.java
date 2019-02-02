package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

	private ParcelamentoDto parcelamento;

	@Inject
	private PagamentoParcelaService parcelaService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {

		System.out.println("INIT PAGAMENTO " + this.pagamento);

		this.parcelamento = new ParcelamentoDto(this.pagamento);
		this.parcelamento.setPrimeiroVencimento(new Date(System.currentTimeMillis()));

		this.parcelamento.setQuantidadeParcelas(4);
		this.parcelamento.setValorParcela(new BigDecimal(150));
		this.parcelamento.setIntervaloDias(15);
		this.parcelamento.setTipoLancamento(TipoLancamento.MENSAL);

		System.out.println(">>>>>>>>>>>. POST_CONSTRUCTOR " + this.parcelamento);
	}

	public String gerarParcelas() {
		// TODO - Gerar parcelas de pagamento de acordo com formulário de parcelamento
		// parcelaService.salvar(pagamento, parcela);

		System.out.println(">>>>>>>> GERACAO DAS PARCELAS " + this.parcelamento);

		messagesHelper.addFlash(new FacesMessage("Parcelas geradas com sucesso!"));
		return "/pagamento/pagamentocadastro?faces-redirect=true" + "&pagamento=" + this.pagamento.getIdPagamento();
	}

	public void setPagamento(Pagamento pagamento) {

		System.out.println("SET PAGAMENTO >>>>>>>>> " + pagamento);

		this.parcelamento.setPagamento(pagamento);
		this.pagamento = pagamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public ParcelamentoDto getParcelamento() {
		if (this.parcelamento == null) {
			init();
		}
		return parcelamento;
	}

	public void setParcelamento(ParcelamentoDto parcelamento) {

		System.out.println("SET PARCELAMENTO >>>>>>>>> " + parcelamento);

		this.parcelamento = parcelamento;
	}

}
