package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class BaixaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<PagamentoParcela> listarTodos() {
		return manager.createQuery("select p from PagamentoParcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.fornecedor "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "order by p.vencimento ", 
				PagamentoParcela.class).getResultList();
	}

	public List<PagamentoParcela> listarPor(String pesquisa) {
		return manager
				.createQuery("select p from PagamentoParcela p "
						+ "join fetch p.pagamento pg "
						+ "join fetch pg.fornecedor "
						+ "join fetch pg.setor "
						+ "join fetch pg.conta "
						+ "WHERE pg.historico LIKE :pHistorico "
						+ "OR pg.fornecedor.nomeFantasia LIKE :pNomeFantasia "
						+ "OR pg.fornecedor.razaoSocial LIKE :pRazaoSocial "
						+ "order by p.vencimento ", 
						PagamentoParcela.class)
				.setParameter("pHistorico", "%" + pesquisa + "%")
				.setParameter("pNomeFantasia", "%" + pesquisa + "%")
				.setParameter("pRazaoSocial", "%" + pesquisa + "%")
				.getResultList();
		}
	

}
