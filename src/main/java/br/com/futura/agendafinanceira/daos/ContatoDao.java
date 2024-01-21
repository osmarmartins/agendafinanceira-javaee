package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Contato;

public class ContatoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Contato pesquisarPorId(Integer idContato){
		return manager.createQuery("select c from Contato c where c.idContato = :pContato ", Contato.class)
				.setParameter("pContato", idContato)
				.getSingleResult();
	}
	
	public void salvar(Contato contato){
		if (contato.getIdContato()!=null){
			manager.merge(contato);
		}else{
			manager.persist(contato);
		}
	}
	
	public void excluir(Contato contato) {
		manager.remove(manager.getReference(Contato.class, contato.getIdContato()));
	}
}
