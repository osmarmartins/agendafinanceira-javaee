package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Contato;

public class ContatoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Contato pesquisarPorId(Integer idContato){
		return manager.find(Contato.class, idContato);
	}
	
	@Transactional
	public void salvar(Contato contato){
		if (contato.getIdContato()!=null){
			manager.merge(contato);
		}else{
			manager.persist(contato);
		}
	}
	
	@Transactional
	public void excluir(Contato contato) {
		manager.remove(manager.getReference(Contato.class, contato.getIdContato()));
	}
}
