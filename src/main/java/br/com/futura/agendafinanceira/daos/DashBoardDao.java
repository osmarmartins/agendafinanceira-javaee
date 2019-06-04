package br.com.futura.agendafinanceira.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.dto.DespesaMesDto;
import br.com.futura.agendafinanceira.dto.GraficoDto;

public class DashBoardDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<GraficoDto> totalDespesasPorMes(Date dataInicial, Date dataFinal) {
	
		List<GraficoDto> meses = new ArrayList<>();
		
		String sql = "select new br.com.futura.agendafinanceira.dto.DespesaMesDto( "
				+ " year(p.vencimento), month(p.vencimento), "
				+ " sum(p.valor + p.juros + p.mora + p.outros - p.desconto) ) "
				+ " from PagamentoParcela p "
				+ " where p.vencimento between :pDataInicial and :pDataFinal "
				+ " group by year(p.vencimento), month(p.vencimento) "
				+ " order by year(p.vencimento), month(p.vencimento) ";
		
		List<DespesaMesDto> despesas = manager.createQuery(sql, DespesaMesDto.class)
				.setParameter("pDataInicial", dataInicial)
				.setParameter("pDataFinal", dataFinal)
				.getResultList();
		
		for (DespesaMesDto despesa : despesas) {
			meses.add( new GraficoDto(despesa.getReferencia(), despesa.getValor()) );
		}
	
		return meses;
	}

	public List<GraficoDto> totalDespesasPorContas(Date dataInicial, Date dataFinal) {
		
		String sql = "select new br.com.futura.agendafinanceira.dto.GraficoDto( "
				+ " p.pagamento.conta.descricao, "
				+ " sum(p.valor + p.juros + p.mora + p.outros - p.desconto) ) "
				+ " from PagamentoParcela p "
				+ " where p.vencimento between :pDataInicial and :pDataFinal "
				+ " group by p.pagamento.conta.descricao "
				+ " order by sum(p.valor + p.juros + p.mora + p.outros - p.desconto) ";
		
		List<GraficoDto> contas = manager.createQuery(sql, GraficoDto.class)
				.setParameter("pDataInicial", dataInicial)
				.setParameter("pDataFinal", dataFinal)
				.getResultList();
	
		return contas;	
	}
	
}

