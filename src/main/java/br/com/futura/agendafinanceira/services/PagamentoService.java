package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.PagamentoQuitacaoDao;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

@Named
public class PagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoDao pagamentoDao;
	
	public void salvar(Pagamento pagamento) {
		pagamento.setTotal(pagamento.getTotal());
		pagamento.setTotalPago(pagamento.getTotalPago());
		pagamentoDao.salvar(pagamento);
	}


}
