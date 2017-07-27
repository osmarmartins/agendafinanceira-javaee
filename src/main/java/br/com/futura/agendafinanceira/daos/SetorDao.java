package br.com.futura.agendafinanceira.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Setor;

public class SetorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Setor> listarSetores(){
		return manager.createQuery("SELECT s FROM Setor s", Setor.class).getResultList();
	}

	@Transactional
	public void salvar(Setor setor) {
		manager.persist(setor);
		
	}

}
