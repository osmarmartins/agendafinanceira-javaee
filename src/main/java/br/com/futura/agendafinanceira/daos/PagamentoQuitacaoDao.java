package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

@Named
public class PagamentoQuitacaoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<PagamentoQuitacao> listaQuitacoesPor(Pagamento pagamento){
		return manager.createQuery("select q from PagamentoQuitacao q "
				+ "join fetch q.parcela p "
				+ "join fetch p.pagamento pg "
				+ "where pg.idPagamento = :pPagamento ", PagamentoQuitacao.class)
					.setParameter("pPagamento", pagamento.getIdPagamento())
					.getResultList();
	}

}
