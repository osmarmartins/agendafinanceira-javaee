package br.com.futura.agendafinanceira.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Setor;

public class SetorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Setor> listarSetores(){
		return manager.createQuery("SELECT s from Setor s", Setor.class).getResultList();
	}

}
