package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Pagamento;

public class PagamentoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public Pagamento pesquisarPorId(Integer idPagamento) {
		Pagamento pagamento = manager.createQuery("select pg from Pagamento pg "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "join fetch pg.fornecedor "
				+ "left join fetch pg.parcelas p "
				+ "left join fetch p.quitacoes q "
				+ "where pg.idPagamento = :pPagamento", Pagamento.class)
					.setParameter("pPagamento", idPagamento)
					.getSingleResult();
		return pagamento;
	}
	
	public List<Pagamento> listarTodos() {
		return manager.createQuery("select distinct pg from Pagamento pg "
				+ "join fetch pg.fornecedor f "
				+ "left join fetch pg.parcelas p "
				+ "left join fetch p.quitacoes q "
				+ "join fetch pg.conta c ", Pagamento.class)
					.getResultList();
	}

	public List<Pagamento> listarPor(String filtro) {
		return manager.createQuery("select pg from Pagamento pg "
				+ "join fetch pg.fornecedor "
				+ "WHERE pg.historico LIKE :pHistorico "
				+ "OR pg.fornecedor.nomeFantasia LIKE :pNomeFantasia "
				+ "OR pg.fornecedor.razaoSocial LIKE :pRazaoSocial ",Pagamento.class)
					.setParameter("pHistorico", "%" + filtro + "%")
					.setParameter("pNomeFantasia", "%" + filtro + "%")
					.setParameter("pRazaoSocial", "%" + filtro + "%")
					.getResultList();
	}

	public void excluir(List<Pagamento> pagamentos) {
		for (Pagamento pagamento : pagamentos) {
			manager.remove(manager.getReference(Pagamento.class, pagamento.getIdPagamento()));
		}
	}

	public void salvar(Pagamento pagamento) {
		if (pagamento.getIdPagamento() != null){
			manager.merge(pagamento);
		}else{
			manager.persist(pagamento);
		}
		
	}



}
