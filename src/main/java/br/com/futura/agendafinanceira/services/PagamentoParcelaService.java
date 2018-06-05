package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

@Named
public class PagamentoParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoParcelaDao parcelaDao;
	
	public void salvar(Pagamento pagamento, PagamentoParcela parcela) {
		pagamento.addParcela(parcela);
		parcelaDao.salvar(parcela);
	}
	
	public void excluir(Pagamento pagamento, PagamentoParcela parcela) {
		// TODO validar exclusão (não permitir excluir parcela com registro de quitação)
		pagamento.removeParcela(parcela);
		parcelaDao.excluir(parcela);
	}

	public PagamentoParcela pesquisaPorId(Integer idParcela) {
		return parcelaDao.pesquisaPorId(idParcela);
	}
	

}
