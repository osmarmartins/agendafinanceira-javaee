package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.BaixaParcelaDao;
import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

public class BaixaParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BaixaParcelaDao baixaParcelaDao;
	
	@Inject
	private PagamentoParcelaDao parcelaDao;
	
	@Transactional
	public void salvar(PagamentoQuitacao quitacao) {
		
		// TODO: Atualizar situação do pagamento
		
		// TODO: Atualizar situação da parcela
		
		parcelaDao.salvar(quitacao.getParcela());
		baixaParcelaDao.salvar(quitacao);
	}

	@Transactional
	public void excluir(PagamentoQuitacao quitacao) {
		//TODO Realizar as validações para exclusão da quitação
		baixaParcelaDao.excluir(quitacao);
	}

}
