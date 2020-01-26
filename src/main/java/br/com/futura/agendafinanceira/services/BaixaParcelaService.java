package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.BaixaParcelaDao;
import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
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
	public void salvarParcelaUnica(PagamentoParcela parcela, PagamentoQuitacao quitacao) {
		salvar(parcela, quitacao);
	}

	public void salvar(PagamentoParcela parcela, PagamentoQuitacao quitacao) {
		parcela.addQuitacao(quitacao);
//		quitacao.setParcela(parcela);

		Float saldoDevedorParcela = quitacao.getParcela().saldoDevedor().subtract(quitacao.getValor()).floatValue(); 
		if (saldoDevedorParcela == 0){
			quitacao.getParcela().setSituacao(SituacaoParcela.LIQUIDADO);
			parcelaDao.salvar(quitacao.getParcela());
		}
		
		baixaParcelaDao.salvar(quitacao);
		
		pagamento = pagamentoDao.pesquisarPorId(quitacao.getParcela().getPagamento().getIdPagamento());
//		Float saldoDevedorPagamento = pagamento.saldoDevedor().subtract(quitacao.getValor()).floatValue();
		Float saldoDevedorPagamento = pagamento.saldoDevedor().floatValue();
		if (saldoDevedorPagamento <= 0){
			pagamento.setSituacao(SituacaoPagamento.FINALIZADO);
			pagamentoDao.salvar(pagamento);
		}
		
		
	}

}
