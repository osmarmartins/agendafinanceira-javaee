package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.BaixaParcelaDao;
import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.exceptions.ApplicationException;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.models.enums.SituacaoPagamento;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

public class BaixaParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BaixaParcelaDao baixaParcelaDao;
	
	@Inject
	private PagamentoParcelaDao parcelaDao;
	
	@Inject
	private PagamentoDao pagamentoDao;
	
	private Pagamento pagamento;
	
	@Transactional
	public void excluir(PagamentoQuitacao quitacao) {
		//TODO Realizar as validações para exclusão da quitação

		Float saldoDevedorParcela = quitacao.getParcela().saldoDevedor().add(quitacao.getValor()).floatValue(); 
		if (saldoDevedorParcela > 0){
			quitacao.getParcela().setSituacao(SituacaoParcela.AGENDADO);
			parcelaDao.salvar(quitacao.getParcela());
		}
		
		baixaParcelaDao.excluir(quitacao);
		
		pagamento = pagamentoDao.pesquisarPorId(quitacao.getParcela().getPagamento().getIdPagamento());
		Float saldoDevedorPagamento = pagamento.saldoDevedor().add(quitacao.getValor()).floatValue();
		if (saldoDevedorPagamento > 0){
			pagamento.setSituacao(SituacaoPagamento.EMABERTO);
			pagamentoDao.salvar(pagamento);
		}
		
	}
	
	@Transactional
	public PagamentoParcela salvarParcelaUnica(PagamentoParcela parcela, PagamentoQuitacao quitacao) throws ApplicationException {
		return salvar(parcela, quitacao);
		
	}

	public PagamentoParcela salvar(PagamentoParcela parcela, PagamentoQuitacao quitacao) throws ApplicationException {
		
		if (quitacao.getValor().floatValue() > parcela.saldoDevedor().floatValue()) {
			throw new ApplicationException("Valor pago acima do saldo devedor!");
		}
		
		pagamento = pagamentoDao.pesquisarPorId(parcela.getPagamento().getIdPagamento());
		
		float saldoPagamento = pagamento.saldoDevedor().floatValue() - quitacao.getValor().floatValue();
		float saldoParcela = parcela.saldoDevedor().floatValue() - quitacao.getValor().floatValue();
		
		parcela.addQuitacao(quitacao);
		baixaParcelaDao.salvar(quitacao);
		
		if (saldoParcela <= 0){
			parcela.setSituacao(SituacaoParcela.LIQUIDADO);
		}
		parcelaDao.salvar(quitacao.getParcela());
		
		if (saldoPagamento <= 0) {
			pagamento.setSituacao(SituacaoPagamento.FINALIZADO);
		}
		pagamentoDao.salvar(pagamento);
		
		return parcela;
		
	}

}
