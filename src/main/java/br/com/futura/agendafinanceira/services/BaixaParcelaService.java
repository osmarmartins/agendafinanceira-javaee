package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.BaixaParcelaDao;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

@Named
public class BaixaParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BaixaParcelaDao baixaParcelaDao;
	
	public void salvar(PagamentoQuitacao quitacao) {
		baixaParcelaDao.salvar(quitacao);
	}

	public void excluir(PagamentoQuitacao quitacao) {
		//TODO Realizar as validações para exclusão da quitação
		baixaParcelaDao.excluir(quitacao);
	}

}
