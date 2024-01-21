package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Autorizacao;

public class AutorizacaoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Autorizacao> autorizacoes(){
		return manager.createQuery("select a from Autorizacao a order by a.classificacao ", Autorizacao.class).getResultList();
	}

	public Autorizacao pesquisarPorId(Integer id) {
		String sql = "select a from Autorizacao a "
				+ "join fetch a.usuarios "
				+ "where a.idRole = :pId ";
		return manager.createQuery(sql, Autorizacao.class)
		.setParameter("pId", id)
		.getSingleResult();
	}

	public void salvar(Autorizacao autorizacao) {
		if (autorizacao.getIdRole() == null) {
			manager.persist(autorizacao);
		} else {
			manager.merge(autorizacao);
		}		
	}

}
