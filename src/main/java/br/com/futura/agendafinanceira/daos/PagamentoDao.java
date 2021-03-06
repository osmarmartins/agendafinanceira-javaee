package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.futura.agendafinanceira.dto.PagamentoDto;
import br.com.futura.agendafinanceira.dto.PagamentoFiltroDto;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

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
	
	
	@SuppressWarnings("unchecked")
	public Integer contarRegistros(PagamentoFiltroDto filtro) {
		StringBuilder sql = new StringBuilder("select count(p) from PagamentoParcela p ");
		TypedQuery<Long> query = (TypedQuery<Long>) aplicarFiltro(filtro, sql, Long.class);
		return query.getSingleResult().intValue();		
	}	

	@SuppressWarnings("unchecked")
	public List<PagamentoDto> listarPor(PagamentoFiltroDto filtro) {
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


		TypedQuery<PagamentoDto> query = (TypedQuery<PagamentoDto>) aplicarFiltro(filtro, sql, PagamentoDto.class);
		query.setFirstResult(filtro.getPaginacao().getPagina());
		query.setMaxResults(filtro.getPaginacao().getRegistrosPorPagina());
		
		return query.getResultList();	
	}

	private TypedQuery<?> aplicarFiltro(PagamentoFiltroDto filtro, StringBuilder sql, Class<?> classe) {
		sql.append(" join p.pagamento pg ");
		sql.append(" join pg.fornecedor ");
		sql.append(" join pg.setor ");
		sql.append(" join pg.conta ");
		sql.append(" left join p.quitacoes q ");
		sql.append(" where 1=1 ");
		
		if (filtro.getHistorico()!=null) {
			sql.append(" and pg.historico like :pHistorico ");
		}
		
		if (filtro.getFornecedor()!=null) {
			sql.append(" and pg.fornecedor = :pFornecedor ");
		}
		
		if (filtro.getSituacao()!=null) {
			sql.append(" and p.situacao = :pSituacao ");
		}
		
		if (filtro.getDataInicial()!=null) {
			sql.append(" and p.vencimento between :pDataInicial and :pDataFinal ");
		}
		
		if (classe.getTypeName() == PagamentoDto.class.getTypeName()) {
			sql.append( " group by p.idPagamentoParcela ");
		}
		
		if (filtro.getPaginacao().getOrdenacao() != null) {
			sql.append(" order by p." + filtro.getPaginacao().getOrdenacao());
			if (!filtro.getPaginacao().isAscendente()) {
				sql.append(" desc "); 
			}
		}else {
			sql.append( " order by p.vencimento ");
		}
		
		TypedQuery<?> query =  manager.createQuery(sql.toString(), classe);
		
		if (filtro.getHistorico()!=null) {
			query.setParameter("pHistorico", "%" + filtro.getHistorico() + "%");
		}
		
		if (filtro.getFornecedor()!=null) {
			query.setParameter("pFornecedor", filtro.getFornecedor());
		}
		
		if (filtro.getSituacao()!=null) {
			query.setParameter("pSituacao", filtro.getSituacao());
		}
		
		if (filtro.getDataInicial()!=null) {
			query.setParameter("pDataInicial", filtro.getDataInicial());
			query.setParameter("pDataFinal", filtro.getDataFinal());
		}
		
		return query;
					
	}

	public void excluir(List<PagamentoParcela> parcelas) {
		for (PagamentoParcela parcela : parcelas) {
			manager.remove(manager.getReference(Pagamento.class, parcela.getPagamento().getIdPagamento()));
		}
	}

	public Pagamento salvar(Pagamento pagamento) {
		if (pagamento.getIdPagamento() != null){
			manager.merge(pagamento);
		}else{
			manager.persist(pagamento);
		}
		return pagamento;
		
	}

}
