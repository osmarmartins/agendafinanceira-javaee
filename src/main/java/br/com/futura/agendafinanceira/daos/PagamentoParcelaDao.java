package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class PagamentoParcelaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
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

	public void salvar(PagamentoParcela parcela) {
		if (parcela.getIdPagamentoParcela() != null) {
			manager.merge(parcela);
		} else {
			manager.persist(parcela);
		}
	}

	public void excluir(PagamentoParcela parcela) {
		manager.remove(manager.getReference(PagamentoParcela.class, parcela.getIdPagamentoParcela()));
	}

}
