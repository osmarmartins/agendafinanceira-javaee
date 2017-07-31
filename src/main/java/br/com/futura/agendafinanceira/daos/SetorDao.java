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
		if (setor.getIdSetor() != null){
			manager.merge(setor);
		}else{
			manager.persist(setor);
		}
	}
	
	@Transactional
	public void excluir(Setor setor){
		manager.remove(manager.getReference(Setor.class, setor.getIdSetor()));
	}

	public Setor findById(Integer idSetor) {
		return manager.find(Setor.class, idSetor);
	}

}
