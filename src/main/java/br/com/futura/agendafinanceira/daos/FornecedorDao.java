package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Fornecedor;

public class FornecedorDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;

	public List<Fornecedor> listarTodos() {
		return manager.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class).getResultList();
	}

}
