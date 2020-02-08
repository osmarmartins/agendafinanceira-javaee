package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import br.com.futura.agendafinanceira.dto.BaixaFiltroDto;
import br.com.futura.agendafinanceira.dto.PagamentoDto;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

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

	@SuppressWarnings("unchecked")
	public Integer contarRegistros(BaixaFiltroDto filtro) {
		StringBuilder sql = new StringBuilder("select count(p) from PagamentoParcela p ");
		sql.append(" join p.pagamento pg ");
		sql.append(" join pg.fornecedor ");
		sql.append(" join pg.setor ");
		sql.append(" join pg.conta ");
		sql.append(" left join p.quitacoes q ");
		sql.append(" WHERE p.situacao in (:pSituacaoParcelas) ");
		TypedQuery<Long> query = (TypedQuery<Long>) aplicarFiltro(filtro, sql, Long.class);
		return query.getSingleResult().intValue();		
	}

	@SuppressWarnings("unchecked")
	public List<PagamentoDto> listarPor(BaixaFiltroDto filtro) {
		StringBuilder sql = new StringBuilder("select new " + PagamentoDto.class.getName() + "( " );
		sql.append(" pg.idPagamento, ");
		sql.append(" p.idPagamentoParcela, ");
		sql.append(" p.parcela, ");
		sql.append(" p.vencimento, ");
		sql.append(" pg.fornecedor.razaoSocial, ");
		sql.append(" pg.historico, ");
		sql.append(" (p.valor - p.desconto + p.juros + p.mora + p.outros ), ");
		sql.append(" (p.valor - p.desconto + p.juros + p.mora + p.outros ) - coalesce(sum(q.valor), 0) )  ");
		sql.append(" from PagamentoParcela p ");
		sql.append(" join p.pagamento pg ");
		sql.append(" join pg.fornecedor ");
		sql.append(" join pg.setor ");
		sql.append(" join pg.conta ");
		sql.append(" left join p.quitacoes q ");
		sql.append(" WHERE p.situacao in (:pSituacaoParcelas) ");

		TypedQuery<PagamentoDto> query = (TypedQuery<PagamentoDto>) aplicarFiltro(filtro, sql, PagamentoDto.class);

		query.setFirstResult(filtro.getPaginacao().getPagina());
		query.setMaxResults(filtro.getPaginacao().getRegistrosPorPagina());
		
		return query.getResultList();		
		
	}
	
	private TypedQuery<?> aplicarFiltro(BaixaFiltroDto filtro, StringBuilder sql, Class<?> classe) {
		if (filtro.getDataInicial() != null) {
			sql.append(" and p.vencimento between :pDataInicial and :pDataFinal ");
		}
		
		if (filtro.getFornecedor() != null) {
			sql.append(" and pg.fornecedor = :pFornecedor ");
		}
		
		if (filtro.getHistorico() != null) {
			sql.append(" and pg.historico like :pHistorico ");
		}
		
		sql.append( " group by p.idPagamentoParcela ");
		
		if (filtro.getPaginacao().getOrdenacao() != null) {
			sql.append(" order by p." + filtro.getPaginacao().getOrdenacao());
			if (!filtro.getPaginacao().isAscendente()) {
				sql.append(" desc "); 
			}
		}else {		
			sql.append(" order by p.vencimento, pg.fornecedor.razaoSocial ");
		}
		
		TypedQuery<?> query = manager.createQuery(sql.toString(), classe);
		
		query.setParameter("pSituacaoParcelas", Arrays.asList(SituacaoParcela.NOVO, SituacaoParcela.AGENDADO, SituacaoParcela.PROGRAMADO));
		
		if (filtro.getDataInicial() != null) {
			query.setParameter("pDataInicial", filtro.getDataInicial());
			query.setParameter("pDataFinal", filtro.getDataFinal());
		}
		
		if (filtro.getFornecedor() != null) {
			query.setParameter("pFornecedor", filtro.getFornecedor());
		}
		
		if (filtro.getHistorico() != null) {
			query.setParameter("pHistorico", "%" + filtro.getHistorico() + "%");
		}
				
		return query;
		
	}
	

	public List<PagamentoQuitacao> listarPor(RelatorioFiltroDto filtro){
		String sql = preparaSql(filtro);
		TypedQuery<PagamentoQuitacao> query = preparaParametros(filtro, sql);
		return query.getResultList();
	}

	private TypedQuery<PagamentoQuitacao> preparaParametros(RelatorioFiltroDto filtro, String sql) {
		TypedQuery<PagamentoQuitacao> query = manager.createQuery(sql, PagamentoQuitacao.class);
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
