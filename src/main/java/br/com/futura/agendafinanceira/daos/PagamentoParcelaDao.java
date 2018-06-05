package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.services.PagamentoService;

public class PagamentoParcelaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	@Inject 
	private PagamentoService pagamentoService;

	public PagamentoParcela pesquisaPorId2(Integer id) {
		return manager.createQuery("select p from PagamentoParcela p "
				+ "left join fetch p.quitacoes q "
				+ "where p.idPagamentoParcela = :pParcela ", PagamentoParcela.class)
					.setParameter("pParcela", id)
					.getSingleResult();
	}

	public PagamentoParcela pesquisaPorId(Integer id) {
		return manager.createQuery("select p from PagamentoParcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "join fetch pg.fornecedor "
				+ "left join fetch p.quitacoes q "
				+ "where p.idPagamentoParcela = :pParcela ", PagamentoParcela.class)
					.setParameter("pParcela", id)
					.getSingleResult();
	}

	@Transactional
	public void salvar(PagamentoParcela parcela) {
		Pagamento pagamento = parcela.getPagamento();
		
		if (parcela.getIdPagamentoParcela() != null) {
			manager.merge(parcela);
		} else {
			manager.persist(parcela);
		}
		
		pagamento.setTotal(pagamento.getTotal());
		pagamento.setTotalPago(pagamento.getTotalPago());
		pagamentoService.salvar(pagamento);
	}

	@Transactional
	public void excluir(PagamentoParcela parcela) {
		manager.remove(manager.getReference(PagamentoParcela.class, parcela.getIdPagamentoParcela()));
	}

}
