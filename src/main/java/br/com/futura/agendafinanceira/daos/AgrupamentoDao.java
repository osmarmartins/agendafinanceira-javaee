package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Agrupamento;

public class AgrupamentoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Agrupamento> listarTodos(){
		return manager.createQuery("select a from Agrupamento a order by a.descricao ", Agrupamento.class).getResultList();
	}

	public Agrupamento pesquisarPorId(Integer id) {
		return manager.createQuery("select a from Agrupamento a where a.id = :pId ", Agrupamento.class)
				.setParameter("pId", id)
				.getSingleResult();
	}

}
