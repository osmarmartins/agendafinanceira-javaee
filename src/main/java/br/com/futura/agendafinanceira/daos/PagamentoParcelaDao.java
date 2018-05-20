package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class PagamentoParcelaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public PagamentoParcela pesquisaPorId(Integer id) {
		return manager.find(PagamentoParcela.class, id);
	}

	@Transactional
	public void salvar(PagamentoParcela parcela) {
		if (parcela.getIdPagamentoParcela() != null) {
			manager.merge(parcela);
		} else {
			manager.persist(parcela);
		}
	}

	@Transactional
	public void excluir(PagamentoParcela parcela) {
		manager.remove(manager.getReference(PagamentoParcela.class, parcela.getIdPagamentoParcela()));
	}

}
