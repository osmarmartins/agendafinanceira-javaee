package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.enums.Ativo;

public class ContaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public List<Conta> listarTodos() {
		return manager.createQuery("SELECT c FROM Conta c order by descricao ", Conta.class).getResultList();
	}

	public List<Conta> listarAtivos() {
		return manager
				.createQuery("SELECT c FROM Conta c where c.ativo=:pAtivo order by descricao ", Conta.class)
				.setParameter("pAtivo", Ativo.ATIVO)
				.getResultList();
	}

	public List<Conta> listarPorDescricao(String pesquisaDescricao) {
		return manager.createQuery("SELECT c FROM Conta c WHERE c.descricao LIKE :pDescricao", Conta.class)
				.setParameter("pDescricao", "%" + pesquisaDescricao + "%")
				.getResultList();
	}

	public void excluir(List<Conta> contas) {
		for (Conta conta : contas) {
			manager.remove(manager.getReference(Conta.class, conta.getIdConta()));
		}
	}
	
	public void salvar(Conta conta) {
		if (conta.getIdConta() != null) {
			manager.merge(conta);
		} else {
			manager.persist(conta);
		}
	}

	public Conta pesquisarPorId(Integer idConta) {
		return manager.createQuery("select c from Conta c where c.idConta = :pConta ", Conta.class)
				.setParameter("pConta", idConta)
				.getSingleResult();
	}
	
}
