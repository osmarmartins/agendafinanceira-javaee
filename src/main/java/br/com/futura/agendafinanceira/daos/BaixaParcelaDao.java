package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

@Named
public class BaixaParcelaDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(PagamentoQuitacao quitacao) {
		if (quitacao.getIdPgtoQuitacao() == null) {
			manager.persist(quitacao);
		}else {
			manager.merge(quitacao);
		}
	}
	
	@Transactional
	public void excluir(PagamentoQuitacao quitacao) {
		manager.remove(manager.getReference(PagamentoQuitacao.class, quitacao.getIdPgtoQuitacao()));
	}

}
