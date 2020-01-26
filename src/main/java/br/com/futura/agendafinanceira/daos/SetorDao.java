package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.Ativo;

public class SetorDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	public List<Setor> listarTodos(){
		return manager.createQuery("SELECT s FROM Setor s order by s.descricao ", Setor.class).getResultList();
	}

	public List<Setor> listarAtivos() {
		return manager
				.createQuery("SELECT s FROM Setor s where s.ativo = :pAtivo order by s.descricao ", Setor.class)
				.setParameter("pAtivo", Ativo.ATIVO)
				.getResultList();
				
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

	public void salvar(Setor setor) {
		if (setor.getIdSetor() != null){
			manager.merge(setor);
		}else{
			manager.persist(setor);
		}
	}
	
	public void excluir(List<Setor> setores){
		for (Setor setor : setores) {
			manager.remove(manager.getReference(Setor.class, setor.getIdSetor()));
		}
	}

}
