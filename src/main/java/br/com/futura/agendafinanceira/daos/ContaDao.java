package br.com.futura.agendafinanceira.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Conta;

public class ContaDao {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Conta> listarContas(){	
		return manager.createQuery("SELECT c FROM Conta c", Conta.class).getResultList();
	}
}
