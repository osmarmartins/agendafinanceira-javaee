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
	
	@Inject
	private PagamentoQuitacaoDao pagamentoQuitacaoDao;
	
	public void salvar(Pagamento pagamento) {
		calcularTotais(pagamento);
		pagamentoDao.salvar(pagamento);
	}
	
	public void calcularTotais(Pagamento pagamento) {
		calculaTotalParcelas(pagamento);
		calculaTotalPago(pagamento);
	}

	private void calculaTotalPago(Pagamento pagamento) {
		BigDecimal total = BigDecimal.ZERO;
		for (PagamentoQuitacao quitacao : pagamentoQuitacaoDao.listaQuitacoesPor(pagamento)) {
			total = total.add(quitacao.getValor());
		}
		pagamento.setTotalPago(total);
		
	}

	private void calculaTotalParcelas(Pagamento pagamento) {
		BigDecimal total = BigDecimal.ZERO;
		for (PagamentoParcela parcela : pagamento.getParcelas()) {
			total = total.add(parcela.getTotalParcela());
		}
		pagamento.setTotal(total);
	}

}
