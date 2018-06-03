package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.BaixaParcelaDao;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

@Named
public class BaixaParcelaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BaixaParcelaDao baixaParcelaDao;
	
	public void salvar(PagamentoParcela parcela, PagamentoQuitacao quitacao) {
		quitacao.setParcela(parcela);
		parcela.addQuitacao(quitacao);
		baixaParcelaDao.salvar(quitacao);
		

		System.out.println("PARCELA >>>>>>>>>>>> " + parcela);
		System.out.println("BAIXA >>>>>>>>>>>> " + quitacao);
		
		
		
	}

}
