package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class PagamentoParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoParcelaDao parcelaDao;

	public PagamentoParcela pesquisaPorId(Integer idParcela) {
		return parcelaDao.pesquisaPorId(idParcela);
	}

	@Transactional
	public void salvar(Pagamento pagamento, PagamentoParcela parcela) {
		pagamento.addParcela(parcela);
		parcelaDao.salvar(parcela);
	}

	@Transactional
	public void excluir(PagamentoParcela parcela) {
		// TODO validar exclusão (não permitir excluir parcela com registro de quitação)
		parcelaDao.excluir(parcela);
		parcela.getPagamento().removeParcela(parcela);
	}

}
