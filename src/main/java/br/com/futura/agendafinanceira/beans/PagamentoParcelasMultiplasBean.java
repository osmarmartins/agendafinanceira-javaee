package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.dto.ParcelamentoDto;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class PagamentoParcelasMultiplasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pagamento pagamento;
	
	private Integer qtdParcelas;
	
	@Inject
	private ParcelamentoDto parcelamento;

	@Inject
	private PagamentoParcelaService parcelaService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.parcelamento = new ParcelamentoDto();

//		System.out.println("INIT PAGAMENTO " + this.pagamento);
//
//		this.parcelamento = new ParcelamentoDto(this.pagamento);
//		this.parcelamento.setPrimeiroVencimento(new Date(System.currentTimeMillis()));
//
//		this.parcelamento.setQuantidadeParcelas(4);
//		this.parcelamento.setValorParcela(new BigDecimal(150));
//		this.parcelamento.setIntervaloDias(15);
//		this.parcelamento.setTipoLancamento(TipoLancamento.MENSAL);
//
//		System.out.println(">>>>>>>>>>>. POST_CONSTRUCTOR " + this.parcelamento);
	}

	public String gerarParcelas() {
		// TODO - Gerar parcelas de pagamento de acordo com formulário de parcelamento
		// parcelaService.salvar(pagamento, parcela);

//		if (this.parcelamento != null) {
//			this.parcelamento.setPagamento(pagamento);
//			this.parcelamento.setQuantidadeParcelas(qtdParcelas);
//			
//		}else {
//			System.out.println("NULL <<<<<<<<<<<<<<<<<<<<< ");
//		}
		
		System.out.println(">>>>>>>> GERACAO DAS PARCELAS " + this.parcelamento);

		this.parcelamento.setPagamento(pagamento);
		
		messagesHelper.addFlash(new FacesMessage("Parcelas geradas com sucesso!" + this.parcelamento));
		return "/pagamento/pagamentocadastro?faces-redirect=true" + "&pagamento=" + this.pagamento.getIdPagamento();
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pagamento getPagamento() {
		if (pagamento == null) {
			this.pagamento = new Pagamento();
		}
		return pagamento;
	}

	public ParcelamentoDto getParcelamento() {
		if (this.parcelamento == null) {
			this.parcelamento = new ParcelamentoDto(this.pagamento);
		}
		return this.parcelamento;
	}

	public void setParcelamento(ParcelamentoDto parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

}
