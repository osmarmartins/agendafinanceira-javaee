package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

@Named
public class PagamentoParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoParcelaDao parcelaDao;
	
	public void salvar(PagamentoParcela parcela) {
		if (parcela.getIdPagamentoParcela() == null) {
			parcela.setSituacao(SituacaoParcela.NOVO);
		}
		parcelaDao.salvar(parcela);
	}
	
	public void excluir(PagamentoParcela parcela) {
		// validar exclusão (não permitir excluir parcela com registro de quitação)
		parcelaDao.excluir(parcela);
	}
	

}
