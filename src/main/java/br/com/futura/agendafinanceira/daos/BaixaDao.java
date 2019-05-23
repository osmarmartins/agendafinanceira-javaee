package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import br.com.futura.agendafinanceira.dto.BaixaFiltroDto;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

public class BaixaDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<PagamentoParcela> listarTodos() {
		return manager.createQuery("select distinct p from PagamentoParcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.fornecedor "
				+ "join fetch pg.setor "
				+ "join fetch pg.conta "
				+ "left join fetch p.quitacoes q "
				+ "order by p.vencimento, p.parcela ", 
				PagamentoParcela.class).getResultList();
	}

	public List<PagamentoParcela> listarPor(BaixaFiltroDto filtro) {
		StringBuilder sql = new StringBuilder("select p from PagamentoParcela p ");
			sql.append(" join fetch p.pagamento pg ");
			sql.append(" join fetch pg.fornecedor ");
			sql.append(" join fetch pg.setor ");
			sql.append(" join fetch pg.conta ");
			sql.append(" left join fetch p.quitacoes q ");
			sql.append(" WHERE p.situacao in (0, 1, 2) ");
		
		if (filtro.getDataInicial() != null) {
			sql.append(" and p.vencimento between :pDataInicial and :pDataFinal ");
		}
		
		if (filtro.getFornecedor() != null) {
			sql.append(" and pg.fornecedor = :pFornecedor ");
		}
		
		sql.append(" order by p.vencimento, pg.fornecedor.razaoSocial ");
		
		TypedQuery<PagamentoParcela> query = manager.createQuery(sql.toString(), PagamentoParcela.class);
		
		if (filtro.getDataInicial() != null) {
			query.setParameter("pDataInicial", filtro.getDataInicial());
			query.setParameter("pDataFinal", filtro.getDataFinal());
		}
		
		if (filtro.getFornecedor() != null) {
			query.setParameter("pFornecedor", filtro.getFornecedor());
		}
				
		return query.getResultList();
	}
	

	public List<PagamentoQuitacao> listarPor(RelatorioFiltroDto filtro){
		String sql = preparaSql(filtro);
		Query query = preparaParametros(filtro, sql);
		return query.getResultList();
	}

	private Query preparaParametros(RelatorioFiltroDto filtro, String sql) {
		Query query = manager.createQuery(sql, PagamentoQuitacao.class);
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
		String sql = "select q from PagamentoQuitacao q "
				+ "join fetch q.parcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.setor s "
				+ "join fetch pg.conta c "
				+ "join fetch pg.fornecedor f "
				+ "where q.dtPgto between :dataI and :dataF "
				+ "  and q.parcela.situacao = 3 ";

		if (filtro.getSetor() != null) {
			sql += "and q.parcela.pagamento.setor = :setor ";
		}
		
		if (filtro.getConta() != null) {
			sql += "and q.parcela.pagamento.conta = :conta ";
		}
		
		if (filtro.getFornecedor() != null) {
			sql += "and q.parcela.pagamento.fornecedor = :fornecedor ";
		}
		
		sql += " order by q.dtPgto, q.parcela.pagamento.fornecedor.razaoSocial ";
		
		return sql;
	}
	

}
