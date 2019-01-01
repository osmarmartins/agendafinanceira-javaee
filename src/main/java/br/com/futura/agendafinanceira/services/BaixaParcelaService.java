package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.BaixaParcelaDao;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

public class BaixaParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BaixaParcelaDao baixaParcelaDao;
	
	@Transactional
	public void salvar(PagamentoQuitacao quitacao) {
		baixaParcelaDao.salvar(quitacao);
	}

	@Transactional
	public void excluir(PagamentoQuitacao quitacao) {
		//TODO Realizar as validações para exclusão da quitação
		baixaParcelaDao.excluir(quitacao);
	}

}
