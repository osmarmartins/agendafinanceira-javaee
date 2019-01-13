package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class PagamentoParcelaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	public PagamentoParcela pesquisaPorId(Integer id) {
		return manager.createQuery("select p from PagamentoParcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "join fetch pg.fornecedor "
				+ "left join fetch p.quitacoes q "
				+ "where p.idPagamentoParcela = :pParcela ", PagamentoParcela.class)
					.setParameter("pParcela", id)
					.getSingleResult();
	}
	
	public List<PagamentoParcela> listarPor(RelatorioFiltroDto filtro){
		String sql = preparaSql(filtro);
		Query query = preparaParametros(filtro, sql);
		return query.getResultList();
	}

	private Query preparaParametros(RelatorioFiltroDto filtro, String sql) {
		Query query = manager.createQuery(sql, PagamentoParcela.class);
		query.setParameter("dataI", filtro.getDataInicial(), TemporalType.DATE);
		query.setParameter("dataF", filtro.getDataFinal(), TemporalType.DATE);

		if (filtro.getSetor() != null) {
			query.setParameter("setor", filtro.getSetor());
		}
		
		if (filtro.getConta() != null) {
			query.setParameter("conta", filtro.getConta());
		}
		
		if (filtro.getFornecedor() != null) {
			query.setParameter("fornecedor", filtro.getFornecedor());
		}
		return query;
	}

	private String preparaSql(RelatorioFiltroDto filtro) {
		String sql = "select p from PagamentoParcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "join fetch pg.fornecedor "
				+ "left join fetch p.quitacoes q "
				+ "where p.vencimento between :dataI and :dataF "
				+ "  and p.situacao < 3 ";
		
		if (filtro.getSetor() != null) {
			sql += "and pg.setor = :setor ";
		}
		
		if (filtro.getConta() != null) {
			sql += "and pg.conta = :conta ";
		}
		
		if (filtro.getFornecedor() != null) {
			sql += "and pg.fornecedor = :fornecedor ";
		}
		
		sql += " order by p.vencimento, pg.fornecedor.razaoSocial ";
		
		return sql;
	}
	
	public void salvar(PagamentoParcela parcela) {
		if (parcela.getIdPagamentoParcela() != null) {
			manager.merge(parcela);
		} else {
			manager.persist(parcela);
		}
	}

	public void excluir(PagamentoParcela parcela) {
		manager.remove(manager.getReference(PagamentoParcela.class, parcela.getIdPagamentoParcela()));
	}

}
