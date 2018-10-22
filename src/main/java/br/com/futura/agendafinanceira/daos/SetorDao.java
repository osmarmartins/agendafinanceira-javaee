package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Setor;

public class SetorDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	public List<Setor> listarTodos(){
		return manager.createQuery("SELECT s FROM Setor s", Setor.class).getResultList();
	}
	
	public List<Setor> listarPorDescricao(String pesquisaDescricao) {
		return manager.createQuery("SELECT s FROM Setor s WHERE s.descricao LIKE :pDescricao ", Setor.class)
				.setParameter("pDescricao", "%" + pesquisaDescricao + "%")
				.getResultList();
	}
	
	public Setor pesquisarPorId(Integer idSetor) {
		return manager.createQuery("select s from Setor s where s.idSetor = :pSetor ", Setor.class)
				.setParameter("pSetor", idSetor)
				.getSingleResult();
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
	public void excluir(List<Setor> setores){
		for (Setor setor : setores) {
			manager.find(Setor.class, setor);
			manager.remove(manager.getReference(Setor.class, setor.getIdSetor()));
		}
	}

}
