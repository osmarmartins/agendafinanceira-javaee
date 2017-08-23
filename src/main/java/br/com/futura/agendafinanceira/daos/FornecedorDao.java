package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Fornecedor;

public class FornecedorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public List<Fornecedor> listarTodos() {
		return manager
				.createQuery("SELECT DISTINCT f FROM Fornecedor f LEFT JOIN FETCH f.contatos", Fornecedor.class)
				.getResultList();
	}

	public Fornecedor pesquisaPorId(Integer idFornecedor) {
		return manager
				.createQuery("SELECT DISTINCT f FROM Fornecedor f LEFT JOIN FETCH f.contatos WHERE f.idFornecedor=:pIdFornecedor", Fornecedor.class)
				.setParameter("pIdFornecedor", idFornecedor)
				.getSingleResult();
	}
	
	@Transactional
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getIdFornecedor() != null) {
			manager.merge(fornecedor);
		} else {
			manager.persist(fornecedor);
		}
	}

	@Transactional
	public void excluir(Fornecedor fornecedor) {
		manager.remove(manager.getReference(Fornecedor.class, fornecedor.getIdFornecedor()));
		
	}
	
}
