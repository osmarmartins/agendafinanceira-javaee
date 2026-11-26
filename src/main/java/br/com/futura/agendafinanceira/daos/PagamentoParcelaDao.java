package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

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
	
	public boolean existe(Integer id) {
		return manager.createQuery("select count(p) from PagamentoParcela p "
				+ "where p.idPagamentoParcela = :pParcela ", Long.class)
					.setParameter("pParcela", id)
					.getSingleResult() > 0;
	}

	public List<PagamentoParcela> listarPor(RelatorioFiltroDto filtro){
		String sql = preparaSql(filtro);
		TypedQuery<PagamentoParcela> query = preparaParametros(filtro, sql);
		return query.getResultList();
	}
	
	private TypedQuery<PagamentoParcela> preparaParametros(RelatorioFiltroDto filtro, String sql) {
		TypedQuery<PagamentoParcela> query = manager.createQuery(sql, PagamentoParcela.class);
		query.setParameter("dataI", filtro.getDataInicial(), TemporalType.DATE);
		query.setParameter("dataF", filtro.getDataFinal(), TemporalType.DATE);
		
		if (!filtro.listaSetores().isEmpty()) {
			query.setParameter("setores", filtro.listaSetores());
		}

		if (!filtro.listaContas().isEmpty()) {
			query.setParameter("contas", filtro.listaContas());
		}

		if (!filtro.listaFornecedores().isEmpty()) {
			query.setParameter("fornecedores", filtro.listaFornecedores());
		}
		return query;
	}

	private String preparaSql(RelatorioFiltroDto filtro) {
		String sql = "select p from PagamentoParcela p "
				+ "join fetch p.pagamento pg "
				+ "join fetch pg.setor s "
				+ "join fetch pg.conta c "
				+ "join fetch pg.fornecedor f "
				+ "where p.vencimento between :dataI and :dataF "
				+ "  and p.situacao < 3 ";
		
		if (!filtro.listaSetores().isEmpty()) {
			sql += "and pg.setor in (:setores) ";
		}

		if (!filtro.listaContas().isEmpty()) {
			sql += "and pg.conta in (:contas) ";
		}

		if (!filtro.listaFornecedores().isEmpty()) {
			sql += "and pg.fornecedor in (:fornecedores) ";
		}
		
		sql += " order by p.vencimento, pg.emissao, f.razaoSocial ";
		
		return sql;
	}
	
	public PagamentoParcela salvar(PagamentoParcela parcela) {
		if (parcela.getIdPagamentoParcela() != null) {
			manager.merge(parcela);
		} else {
			manager.persist(parcela);
		}
		return parcela;
	}

	public void excluir(PagamentoParcela parcela) {
		manager.remove(manager.getReference(PagamentoParcela.class, parcela.getIdPagamentoParcela()));
	}

}
