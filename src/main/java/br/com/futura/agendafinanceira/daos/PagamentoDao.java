package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class PagamentoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public Pagamento pesquisarPorId(int idPagamento) {
		return manager.createQuery("select pg from Pagamento pg "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "join fetch pg.fornecedor "
				+ "left join fetch pg.parcelas p "
				+ "where pg.idPagamento = :pPagamento", Pagamento.class)
					.setParameter("pPagamento", idPagamento)
					.getSingleResult();
	}
	
	public List<Pagamento> listarTodos() {
		return manager.createQuery("select pg from Pagamento pg "
				+ "join fetch pg.fornecedor "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				, Pagamento.class).getResultList();

//		
//		return manager.createQuery("select p from PagamentoParcela p "
//				+ "join fetch p.pagamento pg "
//				+ "join fetch pg.fornecedor "
//				+ "join fetch pg.setor "
//				+ "join fetch pg.conta "
//				, PagamentoParcela.class).getResultList();
	}

	public List<Pagamento> listarPor(String pesquisa) {
		return manager
				.createQuery("select pg from Pagamento pg "
						+ "join fetch pg.fornecedor "
						+ "WHERE pg.historico LIKE :pHistorico "
						+ "OR pg.fornecedor.nomeFantasia LIKE :pNomeFantasia "
						+ "OR pg.fornecedor.razaoSocial LIKE :pRazaoSocial ", 
						Pagamento.class)
				.setParameter("pHistorico", "%" + pesquisa + "%")
				.setParameter("pNomeFantasia", "%" + pesquisa + "%")
				.setParameter("pRazaoSocial", "%" + pesquisa + "%")
				.getResultList();
		}

	@Transactional
	public void excluir(Pagamento pagamento) {
		manager.remove(manager.getReference(Pagamento.class, pagamento.getIdPagamento()));
	}

	@Transactional
	public void salvar(Pagamento pagamento) {
		if (pagamento.getIdPagamento() != null){
			manager.merge(pagamento);
		}else{
			manager.persist(pagamento);
		}
		
	}



}
